package com.example.domain.usecase.income.impl

import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.income.inter.SyncLocalChangesIncomesUseCase

class SyncLocalChangesIncomesUseCaseImpl(
    private val incomeRepository: IncomeRepository
) : SyncLocalChangesIncomesUseCase {
    override suspend fun syncLocalChanges() =
        incomeRepository.syncLocalChanges()
}