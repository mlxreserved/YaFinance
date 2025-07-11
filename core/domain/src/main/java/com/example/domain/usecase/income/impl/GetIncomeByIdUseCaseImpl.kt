package com.example.domain.usecase.income.impl

import com.example.domain.model.income.IncomeDetailed
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.income.inter.GetIncomeByIdUseCase
import com.example.model.result.Result

class GetIncomeByIdUseCaseImpl (
    private val incomeRepository: IncomeRepository
) : GetIncomeByIdUseCase {
    override suspend fun getIncomeById(id: Int): Result<IncomeDetailed> =
        incomeRepository.getIncomeById(id)
}