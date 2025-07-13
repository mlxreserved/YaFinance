package com.example.data.repository.income

import com.example.data.mappers.toExpenseDetailed
import com.example.data.mappers.toIncomeDetailed
import com.example.data.mappers.toIncomeDomain
import com.example.data.mappers.toIncomeUpdate
import com.example.data.mappers.toTransactionRequestDTO
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.income.Income
import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.model.result.Result
import com.example.network.api.TransactionApi
import com.example.network.utils.safeCall.safeCallWithRetry
import com.example.utils.extensions.date.dateToString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class IncomeRepositoryImpl @Inject constructor(
    private val transactionApi: TransactionApi,
    private val getAccountIdUseCase: GetAccountIdUseCase
) : IncomeRepository {
    private val emptyStartDate = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 23)
    }.time

    private val emptyEndDate = Date()

    /** Получение расходов за определенный период **/
    override suspend fun getIncomes(
        startDate: Date?,
        endDate: Date?
    ): Result<List<Income>> {
        val remoteStartDate = startDate?.dateToString() ?: emptyStartDate.dateToString()
        val remoteEndDate = endDate?.dateToString() ?: emptyEndDate.dateToString()

        return safeCallWithRetry {
            withContext(Dispatchers.IO) {
                transactionApi.getTransactions(
                    accountId = getAccountId(),
                    startDate = remoteStartDate,
                    endDate = remoteEndDate
                )
                    .filter { it.category.isIncome == true }
                    .sortedByDescending { it.transactionDate }
                    .map { expanse -> expanse.toIncomeDomain() }
            }
        }
    }

    override suspend fun getIncomeById(id: Int): Result<IncomeDetailed> =
        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                transactionApi
                    .getTransactionById(id)
                    .toIncomeDetailed()
            }
        }

    override suspend fun updateIncomeById(
        id: Int,
        income: IncomeUpdate
    ): Result<IncomeDetailed>  =
        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                transactionApi
                    .updateTransactionById(id, income.toTransactionRequestDTO())
            }.toIncomeDetailed()
        }

    override suspend fun deleteIncomeById(id: Int) =
        withContext(Dispatchers.IO) {
            transactionApi.deleteTransactionById(id)
        }

    override suspend fun createIncome(income: IncomeUpdate): Result<IncomeUpdate> =
        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                transactionApi.createTransaction(income.copy(accountId = getAccountId()).toTransactionRequestDTO())
            }.toIncomeUpdate()
        }

    /** Получить ID счета для использования при запросе транзакций **/
    private suspend fun getAccountId(): Int = withContext(Dispatchers.IO) {
        when (val accountId = getAccountIdUseCase.getAccountId()) {
            is Result.Error -> throw UnknownHostException("Не удалось получить accountId")
            is Result.Success<Int> -> accountId.result
        }
    }
}