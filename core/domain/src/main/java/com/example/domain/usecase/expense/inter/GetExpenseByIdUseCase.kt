package com.example.domain.usecase.expense.inter

import com.example.domain.model.expense.Expense
import com.example.domain.model.expense.ExpenseDetailed
import com.example.model.result.Result
import java.util.Date

interface GetExpenseByIdUseCase {
    suspend fun getExpenseById(id: Int): Result<ExpenseDetailed>
}