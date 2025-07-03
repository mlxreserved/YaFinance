package com.example.yafinance.domain.usecase.global.impl

import com.example.yafinance.domain.repositories.global.CurrencyProvider
import com.example.yafinance.domain.usecase.global.inter.SetCurrentCurrencyUseCase
import javax.inject.Inject

class SetCurrentCurrencyUseCaseImpl @Inject constructor(
    private val currencyProvider: CurrencyProvider
): SetCurrentCurrencyUseCase {
    override fun setCurrency(newCurrency: String) =
        currencyProvider.setCurrency(newCurrency)
}