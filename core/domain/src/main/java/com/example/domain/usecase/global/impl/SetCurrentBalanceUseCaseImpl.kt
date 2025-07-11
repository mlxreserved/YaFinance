package com.example.domain.usecase.global.impl

import com.example.domain.repository.global.BalanceProvider
import com.example.domain.usecase.global.inter.SetCurrentBalanceUseCase

class SetCurrentBalanceUseCaseImpl (
    private val balanceProvider: BalanceProvider
) : SetCurrentBalanceUseCase {
    override fun setBalance(newBalance: String) = balanceProvider.setCurrentBalance(newBalance)
}