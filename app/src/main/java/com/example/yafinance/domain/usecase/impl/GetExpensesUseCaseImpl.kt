package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.repositories.TransactionRepository
import com.example.yafinance.domain.usecase.inter.GetExpensesUseCase
import java.util.Date
import javax.inject.Inject

class GetExpensesUseCaseImpl @Inject constructor(
    private val transactionRepository: TransactionRepository
) : GetExpensesUseCase {
    override suspend fun getExpenses(startDate: Date?, endDate: Date?): List<Expense> =
        transactionRepository.getExpenses(startDate = startDate, endDate = endDate)
}