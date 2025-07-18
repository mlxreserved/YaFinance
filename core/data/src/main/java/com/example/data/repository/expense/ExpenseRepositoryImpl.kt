package com.example.data.repository.expense

import com.example.data.mappers.toExpenseDetailed
import com.example.data.mappers.toDomain
import com.example.data.mappers.toEntity
import com.example.data.mappers.toExpenseEntity
import com.example.data.mappers.toExpenseFullInfoEntity
import com.example.data.mappers.toExpenseUpdate
import com.example.data.mappers.toTransactionRequest
import com.example.data.mappers.toTransactionRequestDTO
import com.example.database.dao.ExpenseDao
import com.example.database.entity.expense.ExpenseFullInfo
import com.example.domain.model.expense.Expense
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.repository.expense.ExpenseRepository
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

class ExpenseRepositoryImpl @Inject constructor(
    private val transactionApi: TransactionApi,
    private val getAccountIdUseCase: GetAccountIdUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val expenseDao: ExpenseDao
) : ExpenseRepository {
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
    override suspend fun getExpenses(
        startDate: Date?,
        endDate: Date?
    ): Result<List<Expense>> = safeCallWithRetry {
        val localStartDate = startDate?.toStringWithZone() ?: emptyStartDate.toStringWithZone()
        val localEndDate = endDate?.toStringWithZone() ?: emptyEndDate.toStringWithZone()

        val accountId = getAccountId()

        initializeExpensesDatabase(accountId)

        expenseDao.getNotDeletedExpenses(localStartDate, localEndDate)
            .map { expenseEntity ->
                expenseEntity.toDomain()
            }.sortedByDescending { expense ->
                expense.transactionDate
            }
    }

    private suspend fun initializeExpensesDatabase(accountId: Int) {
        val notDeleted = expenseDao.getNotDeletedExpenses(initStartDate, initEndDate)
        if (notDeleted.isEmpty()) {
            getCategoriesUseCase.getCategories()
            safeCallWithRetry {
                transactionApi.getTransactions(
                    accountId = accountId,
                    startDate = initStartDate,
                    endDate = initEndDate
                )
                    .filter { it.category.isIncome == false }
                    .map { expense -> expense.toExpenseEntity() }
            }.also { result ->
                if (result is Result.Success) {
                    expenseDao.insertAllExpenses(result.result)
                }
            }
        }
    }

    override suspend fun getExpenseById(id: Int): Result<ExpenseDetailed> {
        expenseDao.getExpenseById(id).toExpenseDetailed().also {
            return Result.Success(it)
        }
    }

    override suspend fun updateExpenseById(
        expense: ExpenseUpdate
    ): Result<ExpenseDetailed> {
        val addedExpense = expenseDao.getExpenseById(expense.localId ?: 0)

        expenseDao.updateExpenseByLocalId(
            localId = addedExpense.expense.localId,
            serverId = addedExpense.expense.serverId,
            amount = expense.amount,
            comment = expense.comment,
            transactionDate = expense.transactionDate,
            accountId = expense.accountId,
            categoryId = expense.categoryId
        )

        safeCallWithRetry(
            maxRetries = 0
        ) {
            withContext(Dispatchers.IO) {
                transactionApi
                    .updateTransactionById(addedExpense.expense.serverId ?: 0, expense.toTransactionRequestDTO())
            }.toExpenseFullInfoEntity(isDeleted = false, isAwaiting = true)
        }.also { result ->
            when (result) {
                is Result.Error -> {
                    return result
                }
                is Result.Success<ExpenseFullInfo> -> {
                    expenseDao.updateExpenseAwaitingStatus(
                        localId = result.result.expense.localId,
                        serverId = result.result.expense.serverId
                    )
                    return Result.Success(result.result.toExpenseDetailed())
                }
            }
        }
    }

    override suspend fun deleteExpenseById(localId: Int, serverId: Int?): Result<Unit> {
        expenseDao.deleteExpense(localId, serverId)

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
                        expenseDao.updateExpenseAwaitingStatus(
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

    override suspend fun createExpense(expense: ExpenseUpdate): Result<ExpenseUpdate> =
        safeCallWithRetry(
            maxRetries = 0
        ) {
            val accountId = getAccountId()

            expenseDao.insertExpense(expense.copy(accountId = accountId).toEntity())
            withContext(Dispatchers.IO) {
                transactionApi.createTransaction(
                    expense.copy(accountId = accountId).toTransactionRequestDTO()
                )
            }.toExpenseEntity().toExpenseUpdate()
        }.also { result ->
            when (result) {
                is Result.Error -> return result
                is Result.Success<ExpenseUpdate> -> {
                    expenseDao.updateExpenseAwaitingStatus(
                        localId = result.result.localId ?: 0,
                        serverId = result.result.serverId ?: 0
                    )
                    return Result.Success(result.result)
                }
            }
        }

    override suspend fun syncLocalChanges() {
        val notSyncedChanges = expenseDao.getExpensesAwaitingDispatch()
        val createdLocal = notSyncedChanges.filter { it.expense.serverId == null }
        val changedLocal = notSyncedChanges.filter { it.expense.serverId != null }

        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                createdLocal.forEach { expense ->
                    safeCallWithRetry {
                        transactionApi.createTransaction(
                            expense.toTransactionRequest()
                        ).toExpenseEntity()
                    }.also { result ->
                        if (result is Result.Success) {
                            expenseDao.updateExpenseAwaitingStatus(
                                localId = expense.expense.localId,
                                serverId = result.result.serverId ?: 0
                            )
                        }
                    }
                }
                changedLocal.forEach { expense ->
                    safeCallWithRetry {
                        transactionApi.updateTransactionById(
                            expense.expense.serverId ?: 0,
                            expense.toTransactionRequest()
                        ).toExpenseEntity()
                    }.also { result ->
                        if (result is Result.Success) {
                            expenseDao.updateExpenseAwaitingStatus(
                                localId = expense.expense.localId,
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