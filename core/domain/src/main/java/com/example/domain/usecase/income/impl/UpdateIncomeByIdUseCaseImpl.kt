package com.example.domain.usecase.income.impl

import com.example.domain.model.income.IncomeUpdate
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.income.inter.UpdateIncomeByIdUseCase

class UpdateIncomeByIdUseCaseImpl(
    private val incomeRepository: IncomeRepository
) : UpdateIncomeByIdUseCase {
    override suspend fun updateIncomeById(
        income: IncomeUpdate
    ) = incomeRepository.updateIncomeById(income)
}