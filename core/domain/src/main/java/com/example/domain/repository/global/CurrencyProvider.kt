package com.example.domain.repository.global

import kotlinx.coroutines.flow.StateFlow

interface CurrencyProvider {
    val currentCurrency: StateFlow<String>
    fun setCurrency(newCurrency: String)
}