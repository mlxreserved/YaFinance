package com.example.domain.usecase.global.impl

import com.example.domain.repository.global.CurrencyProvider
import com.example.domain.usecase.global.inter.GetCurrentCurrencyUseCase
import kotlinx.coroutines.flow.StateFlow

class GetCurrentCurrencyUseCaseImpl constructor(
    private val currencyProvider: CurrencyProvider
): GetCurrentCurrencyUseCase {
    override fun getCurrency(): StateFlow<String> =
        currencyProvider.currentCurrency
}