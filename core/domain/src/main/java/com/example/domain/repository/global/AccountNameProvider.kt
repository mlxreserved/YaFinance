package com.example.domain.repository.global

import kotlinx.coroutines.flow.StateFlow

interface AccountNameProvider {
    val currentAccountName: StateFlow<String>
    fun setAccountName(newAccountName: String)
}