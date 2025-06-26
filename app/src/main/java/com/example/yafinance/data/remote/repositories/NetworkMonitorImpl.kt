package com.example.yafinance.data.remote.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.example.yafinance.domain.repositories.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkMonitorImpl @Inject constructor(
    private val context: Context
) : NetworkMonitor {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _isConnected: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val connectivityManager: ConnectivityManager =
        this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var isRegistered = false

    private var lostJob: Job? = null

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            lostJob?.cancel()
            _isConnected.update { true }
        }

        override fun onLost(network: Network) {
            lostJob = scope.launch {
                delay(DELAY_BEFORE_CHECK)
                val isStillConnected = getCurrentConnectivity()
                if (!isStillConnected) {
                    _isConnected.update { false }
                }
            }

        }
    }

    init {
        _isConnected.update { getCurrentConnectivity() }

        registerCallback()
    }


    override fun registerCallback() {
        if (!isRegistered) {
            val request = NetworkRequest.Builder().build()
            connectivityManager.registerNetworkCallback(request, callback)
            isRegistered = true
        }
    }

    override fun unregisterCallback() {
        if (isRegistered) {
            connectivityManager.unregisterNetworkCallback(callback)
            isRegistered = false
            scope.cancel()
        }
    }

    private fun getCurrentConnectivity(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    companion object {
        private const val DELAY_BEFORE_CHECK = 1000L
    }

}