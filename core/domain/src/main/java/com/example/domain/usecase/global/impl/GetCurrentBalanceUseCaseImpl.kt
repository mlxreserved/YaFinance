package com.example.domain.usecase.global.impl

import com.example.domain.repository.global.BalanceProvider
import com.example.domain.usecase.global.inter.GetCurrentBalanceUseCase
import kotlinx.coroutines.flow.StateFlow

class GetCurrentBalanceUseCaseImpl (
    private val balanceProvider: BalanceProvider
): GetCurrentBalanceUseCase {
    override fun getBalance(): StateFlow<String> = balanceProvider.currentBalance
}