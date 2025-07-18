package com.example.domain.repository.expense

import com.example.domain.model.expense.Expense
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.model.result.Result
import java.util.Date

interface ExpenseRepository {
    suspend fun getExpenses(startDate: Date?, endDate: Date?): Result<List<Expense>>

    suspend fun getExpenseById(id: Int): Result<ExpenseDetailed>

    suspend fun updateExpenseById(expense: ExpenseUpdate): Result<ExpenseDetailed>

    suspend fun deleteExpenseById(localId: Int, serverId: Int?): Result<Unit>

    suspend fun createExpense(expense: ExpenseUpdate): Result<ExpenseUpdate>

    suspend fun syncLocalChanges()
}