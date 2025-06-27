package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.domain.repositories.TransactionRepository
import com.example.yafinance.domain.usecase.inter.GetIncomesUseCase
import com.example.yafinance.domain.utils.Result
import java.util.Date
import javax.inject.Inject

class GetIncomesUseCaseImpl @Inject constructor(
    private val transactionRepository: TransactionRepository
) : GetIncomesUseCase {
    override suspend fun getIncomes(startDate: Date?, endDate: Date?): Result<List<Income>> =
        transactionRepository.getIncomes(startDate = startDate, endDate = endDate)
}