package com.example.domain.usecase.expense.inter

import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.model.result.Result

interface UpdateExpenseByIdUseCase {
    suspend fun updateExpenseById(id: Int, expense: ExpenseUpdate): Result<ExpenseDetailed>
}