package com.example.domain.usecase.income.inter

import com.example.domain.model.income.IncomeDetailed
import com.example.model.result.Result

interface GetIncomeByIdUseCase {
    suspend fun getIncomeById(id: Int): Result<IncomeDetailed>
}