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
import java.util.Date
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val financeApi: FinanceApi,
    private val getAccountsUseCase: GetAccountsUseCase
) :
    TransactionRepository {
    override suspend fun getExpenses(
        startDate: Date?,
        endDate: Date?
    ): List<Expense> {
        val remoteStartDate = startDate.dateToString()
        val remoteEndDate = endDate.dateToString()

        return withContext(Dispatchers.IO) {
            financeApi.getTransactions(
                accountId = getAccountId(),
                startDate = remoteStartDate,
                endDate = remoteEndDate
            )
                .filter { it.category.isIncome == false }
                .map { expanse -> expanse.toExpenseDomain() }
        }
    }

    override suspend fun getIncomes(
        startDate: Date?,
        endDate: Date?
    ): List<Income> {
        val remoteStartDate = startDate.dateToString()
        val remoteEndDate = endDate.dateToString()

        return withContext(Dispatchers.IO) {
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

    suspend fun getAccountId(): Int = withContext(Dispatchers.IO) {
        getAccountsUseCase.getAccounts().first().id
    }
}