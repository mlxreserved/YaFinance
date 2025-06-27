package com.example.yafinance.data.remote.repositories

import com.example.yafinance.data.remote.api.TransactionApi
import com.example.yafinance.data.remote.mappers.toExpenseDomain
import com.example.yafinance.data.remote.mappers.toIncomeDomain
import com.example.yafinance.data.remote.utils.DateFormatter.dateToString
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.domain.repositories.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.data.remote.utils.safeCallWithRetry
import com.example.yafinance.domain.usecase.inter.GetAccountIdUseCase
import java.net.UnknownHostException
import java.util.Date
import javax.inject.Inject


/** Репозиторий для работы с транзакциями **/
class TransactionRepositoryImpl @Inject constructor(
    private val transactionApi: TransactionApi,
    private val getAccountIdUseCase: GetAccountIdUseCase
) : TransactionRepository {

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

    /** Получение доходов за определенный период **/
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
                    .map { income -> income.toIncomeDomain() }
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