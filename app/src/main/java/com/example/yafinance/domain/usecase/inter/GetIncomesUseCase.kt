package com.example.yafinance.domain.usecase.inter

import com.example.yafinance.domain.models.income.Income
import java.util.Date

interface GetIncomesUseCase {
    suspend fun getIncomes(startDate: Date? = null, endDate: Date? = null): List<Income>
}