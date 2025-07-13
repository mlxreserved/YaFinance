package com.example.domain.usecase.expense.impl

import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.usecase.expense.inter.UpdateExpenseByIdUseCase

class UpdateExpenseByIdUseCaseImpl(
    private val expenseRepository: ExpenseRepository
) : UpdateExpenseByIdUseCase {
    override suspend fun updateExpenseById(
        id: Int,
        expense: ExpenseUpdate
    ) = expenseRepository.updateExpenseById(id, expense)
}