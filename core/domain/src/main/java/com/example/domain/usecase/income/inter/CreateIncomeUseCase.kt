package com.example.domain.usecase.income.inter

import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.model.result.Result

interface CreateIncomeUseCase {
    suspend fun createIncome(income: IncomeUpdate): Result<IncomeUpdate>
}