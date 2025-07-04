package com.example.yafinance.domain.usecase.global.inter

import kotlinx.coroutines.flow.StateFlow

interface GetCurrentCurrencyUseCase {
    fun getCurrency(): StateFlow<String>
}