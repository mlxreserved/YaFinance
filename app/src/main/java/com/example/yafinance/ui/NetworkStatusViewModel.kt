package com.example.yafinance.ui

import androidx.lifecycle.ViewModel
import com.example.yafinance.domain.repositories.NetworkMonitor
import javax.inject.Inject

class NetworkStatusViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    val isConnected = networkMonitor.isConnected

    override fun onCleared() {
        super.onCleared()
        networkMonitor.unregisterCallback()
    }
}