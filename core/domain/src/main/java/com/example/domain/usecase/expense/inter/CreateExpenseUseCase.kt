package com.example.domain.usecase.expense.inter

import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.model.result.Result

interface CreateExpenseUseCase {
    suspend fun createExpense(expense: ExpenseUpdate): Result<ExpenseUpdate>
}