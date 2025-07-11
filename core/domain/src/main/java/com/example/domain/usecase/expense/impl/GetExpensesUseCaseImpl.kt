package com.example.domain.usecase.expense.impl

import com.example.domain.model.expense.Expense
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.model.result.Result
import java.util.Date

class GetExpensesUseCaseImpl(
    private val expenseRepository: ExpenseRepository
) : GetExpensesUseCase {
    override suspend fun getExpenses(startDate: Date?, endDate: Date?): Result<List<Expense>> =
        expenseRepository.getExpenses(startDate = startDate, endDate = endDate)
}