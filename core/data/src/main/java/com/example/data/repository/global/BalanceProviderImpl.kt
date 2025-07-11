package com.example.data.repository.global

import com.example.domain.repository.global.BalanceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BalanceProviderImpl @Inject constructor() : BalanceProvider {
    private val _currentBalance = MutableStateFlow("")
    override val currentBalance: StateFlow<String> = _currentBalance.asStateFlow()

    override fun setCurrentBalance(newBalance: String) {
        _currentBalance.value = newBalance
    }
}