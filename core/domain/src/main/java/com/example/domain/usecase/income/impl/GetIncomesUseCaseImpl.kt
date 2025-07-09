package com.example.domain.usecase.income.impl

import com.example.domain.model.income.Income
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.income.inter.GetIncomesUseCase
import com.example.model.result.Result
import java.util.Date

class GetIncomesUseCaseImpl(
    private val incomeRepository: IncomeRepository
) : GetIncomesUseCase {
    override suspend fun getIncomes(startDate: Date?, endDate: Date?): Result<List<Income>> =
        incomeRepository.getIncomes(startDate = startDate, endDate = endDate)
}