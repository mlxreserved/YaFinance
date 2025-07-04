package com.example.yafinance.domain.repositories.global

import kotlinx.coroutines.flow.StateFlow

interface AccountNameProvider {
    val currentAccountName: StateFlow<String>
    fun setAccountName(newAccountName: String)
}