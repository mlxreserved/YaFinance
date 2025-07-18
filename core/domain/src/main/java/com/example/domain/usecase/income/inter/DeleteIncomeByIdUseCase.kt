package com.example.domain.usecase.income.inter

import com.example.model.result.Result

interface DeleteIncomeByIdUseCase {
    suspend fun deleteIncomeById(localId: Int, serverId: Int?): Result<Unit>
}