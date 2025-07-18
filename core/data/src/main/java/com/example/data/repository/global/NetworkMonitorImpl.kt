package com.example.data.repository.global

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.example.domain.repository.global.NetworkMonitor
import com.example.domain.usecase.account.inter.SyncLocalChangesAccountUseCase
import com.example.domain.usecase.expense.inter.SyncLocalChangesExpenseUseCase
import com.example.domain.usecase.income.inter.SyncLocalChangesIncomesUseCase
import com.example.utils.qualifiers.ApplicationContext
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

/** Проверка подклчюния пользователя к сети **/
class NetworkMonitorImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val syncLocalChangesIncomesUseCase: SyncLocalChangesIncomesUseCase,
    private val syncLocalChangesAccountUseCase: SyncLocalChangesAccountUseCase,
    private val syncLocalChangesExpenseUseCase: SyncLocalChangesExpenseUseCase
) : NetworkMonitor {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _isConnected: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /** Состояние показывающее есть ли у пользователя подключение к интернету **/
    override val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val connectivityManager: ConnectivityManager =
        this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var isRegistered = false

    /** Job в которой выполняется работа при потери сети **/
    private var lostJob: Job? = null

    private var syncJob: Job? = null

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            syncJob = scope.launch {
                syncLocalChangesAccountUseCase.syncLocalChanges()
                syncLocalChangesIncomesUseCase.syncLocalChanges()
                syncLocalChangesExpenseUseCase.syncLocalChanges()
            }
            lostJob?.cancel()
            _isConnected.update { true }
        }

        override fun onLost(network: Network) {
            lostJob = scope.launch {
                delay(DELAY_BEFORE_CHECK)
                val isStillConnected = getCurrentConnectivity()
                syncJob?.cancel()
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

    /** Установка callback для мониторинга состояния сети **/
    override fun registerCallback() {
        if (!isRegistered) {
            val request = NetworkRequest.Builder().build()
            connectivityManager.registerNetworkCallback(request, callback)
            isRegistered = true
        }
    }

    /** Отмена callback для мониторинга состояния сети **/
    override fun unregisterCallback() {
        if (isRegistered) {
            connectivityManager.unregisterNetworkCallback(callback)
            isRegistered = false
            scope.cancel()
        }
    }

    /**
     * Получить текущее состояние сети пользователя
     *
     * Необходимо в случае, когда пользователь переключается с WiFi на мобильную сеть
     * **/
    private fun getCurrentConnectivity(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    companion object {
        private const val DELAY_BEFORE_CHECK = 1000L
    }
}