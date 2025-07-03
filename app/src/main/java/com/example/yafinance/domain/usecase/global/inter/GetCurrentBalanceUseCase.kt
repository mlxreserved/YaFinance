package com.example.yafinance.domain.usecase.global.inter

import kotlinx.coroutines.flow.StateFlow

interface GetCurrentBalanceUseCase {
    fun getBalance(): StateFlow<String>
}