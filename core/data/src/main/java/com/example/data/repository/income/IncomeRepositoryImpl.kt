package com.example.data.repository.income

import com.example.data.mappers.toDomain
import com.example.data.mappers.toEntity
import com.example.data.mappers.toIncomeDetailed
import com.example.data.mappers.toIncomeEntity
import com.example.data.mappers.toIncomeFullInfoEntity
import com.example.data.mappers.toIncomeUpdate
import com.example.data.mappers.toTransactionRequest
import com.example.data.mappers.toTransactionRequestDTO
import com.example.database.dao.IncomeDao
import com.example.database.entity.income.IncomeFullInfo
import com.example.domain.model.income.Income
import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.domain.usecase.category.inter.GetCategoriesUseCase
import com.example.model.result.Result
import com.example.network.api.TransactionApi
import com.example.network.utils.safeCall.safeCallWithRetry
import com.example.utils.extensions.date.dateToString
import com.example.utils.extensions.string.toStringWithZone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class IncomeRepositoryImpl @Inject constructor(
    private val transactionApi: TransactionApi,
    private val getAccountIdUseCase: GetAccountIdUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val incomeDao: IncomeDao
) : IncomeRepository {
    private val emptyStartDate = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 23)
    }.time

    private val emptyEndDate = Date()

    private val initStartDate = Date(0).dateToString()

    private val initEndDate = Calendar.getInstance().apply {
        set(Calendar.YEAR, 2050)
        set(Calendar.MONTH, 1)
        set(Calendar.DAY_OF_MONTH, 1)
    }.time.dateToString()

    /** Получение расходов за определенный период **/
    override suspend fun getIncomes(
        startDate: Date?,
        endDate: Date?
    ): Result<List<Income>> = safeCallWithRetry {
        val localStartDate = startDate?.toStringWithZone() ?: emptyStartDate.toStringWithZone()
        val localEndDate = endDate?.toStringWithZone() ?: emptyEndDate.toStringWithZone()

        val accountId = getAccountId()

        initializeIncomesDatabase(accountId)

        incomeDao.getNotDeletedIncomes(localStartDate, localEndDate)
            .map { incomeEntity ->
                incomeEntity.toDomain()
            }.sortedByDescending { income ->
                income.transactionDate
            }
    }

    private suspend fun initializeIncomesDatabase(accountId: Int) {
        val notDeleted = incomeDao.getNotDeletedIncomes(initStartDate, initEndDate)
        if (notDeleted.isEmpty()) {
            getCategoriesUseCase.getCategories()
            safeCallWithRetry {
                transactionApi.getTransactions(
                    accountId = accountId,
                    startDate = initStartDate,
                    endDate = initEndDate
                )
                    .filter { it.category.isIncome == true }
                    .map { income -> income.toIncomeEntity() }
            }.also { result ->
                if (result is Result.Success) {
                    incomeDao.insertAllIncomes(result.result)
                }
            }
        }
    }

    override suspend fun getIncomeById(id: Int): Result<IncomeDetailed> {
        incomeDao.getIncomeById(id).toIncomeDetailed().also {
            return Result.Success(it)
        }
    }

    override suspend fun updateIncomeById(
        income: IncomeUpdate
    ): Result<IncomeDetailed> {
        val addedIncome = incomeDao.getIncomeById(income.localId ?: 0)

        incomeDao.updateIncomeByLocalId(
            localId = addedIncome.income.localId,
            serverId = addedIncome.income.serverId,
            amount = income.amount,
            comment = income.comment,
            transactionDate = income.transactionDate,
            accountId = income.accountId,
            categoryId = income.categoryId
        )

        safeCallWithRetry(
            maxRetries = 0
        ) {
            withContext(Dispatchers.IO) {
                transactionApi
                    .updateTransactionById(
                        addedIncome.income.serverId ?: 0,
                        income.toTransactionRequestDTO()
                    )
            }.toIncomeFullInfoEntity(isDeleted = false, isAwaiting = true)
        }.also { result ->
            when (result) {
                is Result.Error -> {
                    return result
                }

                is Result.Success<IncomeFullInfo> -> {
                    incomeDao.updateIncomeAwaitingStatus(
                        localId = result.result.income.localId,
                        serverId = result.result.income.serverId
                    )
                    return Result.Success(result.result.toIncomeDetailed())
                }
            }
        }
    }

    override suspend fun deleteIncomeById(localId: Int, serverId: Int?): Result<Unit> {
        incomeDao.deleteIncome(localId, serverId)

        if (serverId != null) {
            safeCallWithRetry(
                maxRetries = 0
            ) {
                withContext(Dispatchers.IO) {
                    transactionApi.deleteTransactionById(serverId)
                }
            }.also { result ->
                when (result) {
                    is Result.Error -> return result
                    is Result.Success<Unit> -> {
                        incomeDao.updateIncomeAwaitingStatus(
                            localId = localId,
                            serverId = serverId
                        )
                        return Result.Success(Unit)
                    }
                }
            }
        } else {
            return Result.Success(Unit)
        }
    }

    override suspend fun createIncome(income: IncomeUpdate): Result<IncomeUpdate> =
        safeCallWithRetry(
            maxRetries = 0
        ) {
            val accountId = getAccountId()

            incomeDao.insertIncome(income.copy(accountId = accountId).toEntity())
            withContext(Dispatchers.IO) {
                transactionApi.createTransaction(
                    income.copy(accountId = accountId).toTransactionRequestDTO()
                )
            }.toIncomeEntity().toIncomeUpdate()
        }.also { result ->
            when (result) {
                is Result.Error -> return result
                is Result.Success<IncomeUpdate> -> {
                    incomeDao.updateIncomeAwaitingStatus(
                        localId = result.result.localId ?: 0,
                        serverId = result.result.serverId ?: 0
                    )
                    return Result.Success(result.result)
                }
            }
        }

    override suspend fun syncLocalChanges() {
        val notSyncedChanges = incomeDao.getIncomesAwaitingDispatch()
        val createdLocal = notSyncedChanges.filter { it.income.serverId == null }
        val changedLocal = notSyncedChanges.filter { it.income.serverId != null }

        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                createdLocal.forEach { income ->
                    safeCallWithRetry {
                        transactionApi.createTransaction(
                            income.toTransactionRequest()
                        ).toIncomeEntity()
                    }.also { result ->
                        if (result is Result.Success) {
                            incomeDao.updateIncomeAwaitingStatus(
                                localId = income.income.localId,
                                serverId = result.result.serverId ?: 0
                            )
                        }
                    }
                }
                changedLocal.forEach { income ->
                    safeCallWithRetry {
                        transactionApi.updateTransactionById(
                            income.income.serverId ?: 0,
                            income.toTransactionRequest()
                        ).toIncomeEntity()
                    }.also { result ->
                        if (result is Result.Success) {
                            incomeDao.updateIncomeAwaitingStatus(
                                localId = income.income.localId,
                                serverId = result.result.serverId ?: 0
                            )
                        }
                    }
                }
            }
        }
    }

    /** Получить ID счета для использования при запросе транзакций **/
    private suspend fun getAccountId(): Int = withContext(Dispatchers.IO) {
        when (val accountId = getAccountIdUseCase.getAccountId()) {
            is Result.Error -> throw UnknownHostException("Не удалось получить accountId")
            is Result.Success<Int> -> accountId.result
        }
    }
}