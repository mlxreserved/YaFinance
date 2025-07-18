package com.example.domain.usecase.expense.impl

import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.usecase.expense.inter.DeleteExpenseByIdUseCase
import com.example.model.result.Result

class DeleteExpenseByIdUseCaseImpl(
    private val expenseRepository: ExpenseRepository
) : DeleteExpenseByIdUseCase {
    override suspend fun deleteExpenseById(localId: Int, serverId: Int?): Result<Unit> =
        expenseRepository.deleteExpenseById(localId, serverId)
}