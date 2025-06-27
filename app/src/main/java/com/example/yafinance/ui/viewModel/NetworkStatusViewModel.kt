package com.example.yafinance.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.yafinance.domain.repositories.NetworkMonitor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStatusViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    val isConnected = networkMonitor.isConnected

    override fun onCleared() {
        super.onCleared()
        networkMonitor.unregisterCallback()
    }
}