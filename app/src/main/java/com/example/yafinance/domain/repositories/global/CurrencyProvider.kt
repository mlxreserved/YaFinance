package com.example.yafinance.domain.repositories.global

import kotlinx.coroutines.flow.StateFlow

interface CurrencyProvider {
    val currentCurrency: StateFlow<String>
    fun setCurrency(newCurrency: String)
}