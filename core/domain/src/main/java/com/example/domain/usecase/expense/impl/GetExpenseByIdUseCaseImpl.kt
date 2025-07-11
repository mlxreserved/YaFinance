package com.example.domain.usecase.expense.impl

import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.usecase.expense.inter.GetExpenseByIdUseCase
import com.example.model.result.Result

class GetExpenseByIdUseCaseImpl (
    private val expenseRepository: ExpenseRepository
) : GetExpenseByIdUseCase {
    override suspend fun getExpenseById(id: Int): Result<ExpenseDetailed> =
        expenseRepository.getExpenseById(id)
}