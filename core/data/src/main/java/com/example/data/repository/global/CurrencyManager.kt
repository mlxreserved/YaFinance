package com.example.data.repository.global

import com.example.domain.repository.global.CurrencyProvider
import com.example.utils.extensions.string.toCurrency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyManager @Inject constructor() : CurrencyProvider {
    private val _currentCurrency = MutableStateFlow("RUB")
    override val currentCurrency: StateFlow<String> = _currentCurrency.asStateFlow()

    override fun setCurrency(newCurrency: String) {
        _currentCurrency.value = newCurrency.toCurrency()
    }
}