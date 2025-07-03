package com.example.yafinance.domain.usecase.global.impl

import com.example.yafinance.domain.repositories.global.CurrencyProvider
import com.example.yafinance.domain.usecase.global.inter.GetCurrentCurrencyUseCase
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCurrentCurrencyUseCaseImpl @Inject constructor(
    private val currencyProvider: CurrencyProvider
): GetCurrentCurrencyUseCase {
    override fun getCurrency(): StateFlow<String> =
        currencyProvider.currentCurrency
}