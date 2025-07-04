package com.example.yafinance.domain.usecase.global.impl

import com.example.yafinance.domain.repositories.global.BalanceProvider
import com.example.yafinance.domain.usecase.global.inter.GetCurrentBalanceUseCase
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCurrentBalanceUseCaseImpl @Inject constructor(
    private val balanceProvider: BalanceProvider
): GetCurrentBalanceUseCase {
    override fun getBalance(): StateFlow<String> = balanceProvider.currentBalance
}