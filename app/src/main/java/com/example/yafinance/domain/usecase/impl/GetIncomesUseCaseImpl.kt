package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.domain.repositories.TransactionRepository
import com.example.yafinance.domain.usecase.inter.GetIncomesUseCase
import java.util.Date
import javax.inject.Inject

class GetIncomesUseCaseImpl @Inject constructor(
    private val transactionRepository: TransactionRepository
) : GetIncomesUseCase {
    override suspend fun getIncomes(startDate: Date?, endDate: Date?): List<Income> =
        transactionRepository.getIncomes(startDate = startDate, endDate = endDate)

}