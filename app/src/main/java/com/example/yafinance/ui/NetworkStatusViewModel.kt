package com.example.yafinance.ui

import androidx.lifecycle.ViewModel
import com.example.yafinance.domain.repositories.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkStatusViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    val isConnected = networkMonitor.isConnected

    override fun onCleared() {
        super.onCleared()
        networkMonitor.unregisterCallback()
    }
}