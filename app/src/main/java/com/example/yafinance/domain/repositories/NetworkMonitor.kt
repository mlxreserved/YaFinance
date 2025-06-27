package com.example.yafinance.domain.repositories

import kotlinx.coroutines.flow.StateFlow



interface NetworkMonitor {

    val isConnected: StateFlow<Boolean>

    fun registerCallback()

    fun unregisterCallback()

}