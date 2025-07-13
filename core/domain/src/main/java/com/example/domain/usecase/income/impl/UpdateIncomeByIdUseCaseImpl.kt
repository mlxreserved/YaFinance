package com.example.domain.usecase.income.impl

import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.income.inter.UpdateIncomeByIdUseCase
import com.example.model.result.Result

class UpdateIncomeByIdUseCaseImpl(
    private val incomeRepository: IncomeRepository
) : UpdateIncomeByIdUseCase {
    override suspend fun updateIncomeById(
        id: Int,
        income: IncomeUpdate
    ) = incomeRepository.updateIncomeById(id, income)
}