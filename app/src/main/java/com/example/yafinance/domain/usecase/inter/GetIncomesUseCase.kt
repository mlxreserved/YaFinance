package com.example.yafinance.domain.usecase.inter

import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.domain.utils.Result
import java.util.Date

/** UseCase для получения доходов за период **/
interface GetIncomesUseCase {
    suspend fun getIncomes(startDate: Date? = null, endDate: Date? = null): Result<List<Income>>
}