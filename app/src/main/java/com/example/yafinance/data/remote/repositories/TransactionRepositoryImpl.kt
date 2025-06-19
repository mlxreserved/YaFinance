package com.example.yafinance.data.remote.repositories

import com.example.yafinance.data.remote.api.FinanceApi
import com.example.yafinance.data.remote.mappers.toExpenseDomain
import com.example.yafinance.data.remote.mappers.toIncomeDomain
import com.example.yafinance.data.remote.utils.DateFormatter.dateToString
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.domain.repositories.TransactionRepository
import com.example.yafinance.domain.usecase.inter.GetAccountsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.data.remote.utils.safeCallWithRetry
import com.example.yafinance.domain.models.account.Account
import java.net.UnknownHostException
import java.util.Date
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val financeApi: FinanceApi,
    private val getAccountsUseCase: GetAccountsUseCase
) :
    TransactionRepository {
    private val emptyStartDate = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
    }.time

    private val emptyEndDate = Date()

    override suspend fun getExpenses(
        startDate: Date?,
        endDate: Date?
    ): Result<List<Expense>> {
        val remoteStartDate = startDate?.dateToString() ?: emptyStartDate.dateToString()
        val remoteEndDate = endDate?.dateToString() ?: emptyEndDate.dateToString()

        return safeCallWithRetry {
            withContext(Dispatchers.IO) {
                financeApi.getTransactions(
                    accountId = getAccountId(),
                    startDate = remoteStartDate,
                    endDate = remoteEndDate
                )
                    .filter { it.category.isIncome == false }
                    .map { expanse -> expanse.toExpenseDomain() }
            }
        }
    }

    override suspend fun getIncomes(
        startDate: Date?,
        endDate: Date?
    ): Result<List<Income>> {


        val remoteStartDate = startDate?.dateToString() ?: emptyStartDate.dateToString()
        val remoteEndDate = endDate?.dateToString() ?: emptyEndDate.dateToString()

        return safeCallWithRetry {
            withContext(Dispatchers.IO) {
                financeApi.getTransactions(
                    accountId = getAccountId(),
                    startDate = remoteStartDate,
                    endDate = remoteEndDate
                )
                    .filter { it.category.isIncome == true }
                    .sortedBy { it.transactionDate }
                    .map { expanse -> expanse.toIncomeDomain() }
            }
        }
    }

    suspend fun getAccountId(): Int = withContext(Dispatchers.IO) {
        when(val accounts = getAccountsUseCase.getAccounts()) {
            is Result.Error -> throw UnknownHostException("Не удалось получить accountId")
            is Result.Success<List<Account>> -> accounts.result.first().id
        }
    }
}