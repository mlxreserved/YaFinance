package com.example.data.repository.expense

import com.example.data.mappers.toExpenseDetailed
import com.example.data.mappers.toExpenseDomain
import com.example.data.mappers.toExpenseUpdate
import com.example.data.mappers.toTransactionRequestDTO
import com.example.domain.model.expense.Expense
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.repository.expense.ExpenseRepository
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

class ExpenseRepositoryImpl @Inject constructor(
    private val transactionApi: TransactionApi,
    private val getAccountIdUseCase: GetAccountIdUseCase
) : ExpenseRepository {
    private val emptyStartDate = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 23)
    }.time

    private val emptyEndDate = Date()

    /** Получение расходов за определенный период **/
    override suspend fun getExpenses(
        startDate: Date?,
        endDate: Date?
    ): Result<List<Expense>> {
        val remoteStartDate = startDate?.dateToString() ?: emptyStartDate.dateToString()
        val remoteEndDate = endDate?.dateToString() ?: emptyEndDate.dateToString()

        return safeCallWithRetry {
            withContext(Dispatchers.IO) {
                transactionApi.getTransactions(
                    accountId = getAccountId(),
                    startDate = remoteStartDate,
                    endDate = remoteEndDate
                )
                    .filter { it.category.isIncome == false }
                    .sortedByDescending { it.transactionDate }
                    .map { expanse -> expanse.toExpenseDomain() }
            }
        }
    }

    override suspend fun getExpenseById(id: Int): Result<ExpenseDetailed> =
        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                transactionApi
                    .getTransactionById(id)
                    .toExpenseDetailed()
            }
        }

    override suspend fun updateExpenseById(
        id: Int,
        expense: ExpenseUpdate
    ): Result<ExpenseDetailed> =
        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                transactionApi
                    .updateTransactionById(id, expense.toTransactionRequestDTO())
            }.toExpenseDetailed()
        }

    override suspend fun deleteExpenseById(id: Int) =
        withContext(Dispatchers.IO) {
            transactionApi.deleteTransactionById(id)
        }

    override suspend fun createExpense(expense: ExpenseUpdate): Result<ExpenseUpdate> =
        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                transactionApi.createTransaction(expense.copy(accountId = getAccountId()).toTransactionRequestDTO())
            }.toExpenseUpdate()
        }

    /** Получить ID счета для использования при запросе транзакций **/
    private suspend fun getAccountId(): Int = withContext(Dispatchers.IO) {
        when (val accountId = getAccountIdUseCase.getAccountId()) {
            is Result.Error -> throw UnknownHostException("Не удалось получить accountId")
            is Result.Success<Int> -> accountId.result
        }
    }
}