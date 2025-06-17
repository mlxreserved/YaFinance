package com.example.yafinance.domain.usecase.inter

import com.example.yafinance.domain.models.expense.Expense
import java.util.Date

interface GetExpensesUseCase {
    suspend fun getExpenses(startDate: Date? = null, endDate: Date? = null): List<Expense>
}