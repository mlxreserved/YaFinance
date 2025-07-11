package com.example.domain.repository.global

import kotlinx.coroutines.flow.StateFlow

interface BalanceProvider {
    val currentBalance: StateFlow<String>
    fun setCurrentBalance(newBalance: String)
}