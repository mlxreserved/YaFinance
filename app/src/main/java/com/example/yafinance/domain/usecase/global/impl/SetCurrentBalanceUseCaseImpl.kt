package com.example.yafinance.domain.usecase.global.impl

import com.example.yafinance.domain.repositories.global.BalanceProvider
import com.example.yafinance.domain.usecase.global.inter.SetCurrentBalanceUseCase
import javax.inject.Inject

class SetCurrentBalanceUseCaseImpl @Inject constructor(
    private val balanceProvider: BalanceProvider
) : SetCurrentBalanceUseCase {
    override fun setBalance(newBalance: String) = balanceProvider.setCurrentBalance(newBalance)
}