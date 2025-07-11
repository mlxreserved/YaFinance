package com.example.domain.usecase.income.inter

import com.example.domain.model.income.Income
import com.example.model.result.Result
import java.util.Date

/** UseCase для получения доходов за период **/
interface GetIncomesUseCase {
    suspend fun getIncomes(startDate: Date? = null, endDate: Date? = null): Result<List<Income>>
}