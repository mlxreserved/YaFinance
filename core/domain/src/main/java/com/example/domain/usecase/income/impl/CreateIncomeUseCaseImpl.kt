package com.example.domain.usecase.income.impl

import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.income.inter.CreateIncomeUseCase
import com.example.model.result.Result

class CreateIncomeUseCaseImpl (
    private val incomeRepository: IncomeRepository
): CreateIncomeUseCase {
    override suspend fun createIncome(income: IncomeUpdate): Result<IncomeUpdate> =
        incomeRepository.createIncome(income)
}