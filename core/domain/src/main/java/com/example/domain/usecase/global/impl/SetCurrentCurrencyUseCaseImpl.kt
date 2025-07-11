package com.example.domain.usecase.global.impl

import com.example.domain.repository.global.CurrencyProvider
import com.example.domain.usecase.global.inter.SetCurrentCurrencyUseCase

class SetCurrentCurrencyUseCaseImpl (
    private val currencyProvider: CurrencyProvider
): SetCurrentCurrencyUseCase {
    override fun setCurrency(newCurrency: String) =
        currencyProvider.setCurrency(newCurrency)
}