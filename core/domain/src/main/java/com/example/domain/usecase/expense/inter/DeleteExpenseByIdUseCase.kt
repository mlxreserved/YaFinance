package com.example.domain.usecase.expense.inter

import com.example.model.result.Result

interface DeleteExpenseByIdUseCase {
    suspend fun deleteExpenseById(localId: Int, serverId: Int?): Result<Unit>
}