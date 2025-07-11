package com.example.ui.networkMonitor

import androidx.lifecycle.ViewModel
import com.example.domain.repository.global.NetworkMonitor
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