package com.example.domain.repository.expense

import com.example.domain.model.expense.Expense
import com.example.model.result.Result
import java.util.Date

interface ExpenseRepository {
    suspend fun getExpenses(startDate: Date?, endDate: Date?): Result<List<Expense>>
}