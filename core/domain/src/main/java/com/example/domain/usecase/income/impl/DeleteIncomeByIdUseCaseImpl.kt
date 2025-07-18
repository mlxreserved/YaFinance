package com.example.domain.usecase.income.impl

import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.income.inter.DeleteIncomeByIdUseCase
import com.example.model.result.Result

class DeleteIncomeByIdUseCaseImpl(
    private val incomeRepository: IncomeRepository
) : DeleteIncomeByIdUseCase {
    override suspend fun deleteIncomeById(localId: Int, serverId: Int?): Result<Unit> =
        incomeRepository.deleteIncomeById(localId, serverId)
}