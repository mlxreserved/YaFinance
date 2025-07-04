package com.example.yafinance.domain.repositories.global

import kotlinx.coroutines.flow.StateFlow

interface BalanceProvider {
    val currentBalance: StateFlow<String>
    fun setCurrentBalance(newBalance: String)
}