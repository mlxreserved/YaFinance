package com.example.yafinance.domain.usecase.expense.impl

import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.repositories.transaction.TransactionRepository
import com.example.yafinance.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.yafinance.domain.utils.Result
import java.util.Date
import javax.inject.Inject

class GetExpensesUseCaseImpl @Inject constructor(
    private val transactionRepository: TransactionRepository
) : GetExpensesUseCase {
    override suspend fun getExpenses(startDate: Date?, endDate: Date?): Result<List<Expense>> =
        transactionRepository.getExpenses(startDate = startDate, endDate = endDate)
}