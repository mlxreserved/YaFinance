package com.example.yafinance.ui.screens.accounts

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.usecase.account.inter.GetAccountUseCase
import com.example.yafinance.domain.usecase.global.inter.GetCurrentAccountNameUseCase
import com.example.yafinance.domain.usecase.global.inter.GetCurrentBalanceUseCase
import com.example.yafinance.domain.usecase.global.inter.GetCurrentCurrencyUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.BaseViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountsViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    getCurrentCurrencyUseCase: GetCurrentCurrencyUseCase,
    getCurrentAccountNameUseCase: GetCurrentAccountNameUseCase,
    getCurrentBalanceUseCase: GetCurrentBalanceUseCase
) : BaseViewModel<Account>() {
    private var currentCurrency = getCurrentCurrencyUseCase.getCurrency()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = "RUB"
        )

    private var currentAccountName = getCurrentAccountNameUseCase.getAccountName()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ""
        )

    private var currentBalance = getCurrentBalanceUseCase.getBalance()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ""
        )

    init {
        observeBalanceChanges()
        observeAccountNameChanges()
        observeCurrencyChanges()
        loadAccounts()
    }

    private fun loadAccounts(isRetried: Boolean = false) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            when (val response = getAccountUseCase.getAccounts()) {
                is Result.Success -> updateState(ScreenState.Success(result = response.result))
                is Result.Error -> updateState(
                    ScreenState.Error(
                        message = response.error,
                        isRetried
                    )
                )
            }
        }
    }

    private fun observeCurrencyChanges() {
        viewModelScope.launch {
            currentCurrency.collect { currency ->
                val state = screenState.value
                if (state is ScreenState.Success) {
                    val updated = state.result.copy(currency = currency)
                    updateState(ScreenState.Success(updated))
                }
            }
        }
    }

    private fun observeAccountNameChanges() {
        viewModelScope.launch {
            currentAccountName.collect { accountName ->
                val state = screenState.value
                if (state is ScreenState.Success) {
                    val updated = state.result.copy(name = accountName)
                    updateState(ScreenState.Success(updated))
                }
            }
        }
    }

    private fun observeBalanceChanges() {
        viewModelScope.launch {
            currentBalance.collect { sum ->
                val state = screenState.value
                if (state is ScreenState.Success) {
                    val updated = state.result.copy(sum = sum)
                    updateState(ScreenState.Success(updated))
                }
            }
        }
    }

    fun onRetryClicked() {
        loadAccounts(isRetried = true)
    }
}