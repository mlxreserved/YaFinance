package com.example.yafinance.ui.screens.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.usecase.inter.GetAccountsUseCase
import com.example.yafinance.ui.utils.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(private val getAccountsUseCase: GetAccountsUseCase) :
    ViewModel() {

    private val _accountsState: MutableStateFlow<ScreenState<Account>> =
        MutableStateFlow(ScreenState.Loading)
    val accountsState: StateFlow<ScreenState<Account>> = _accountsState.asStateFlow()

    init {
        loadAccounts()
    }

    private fun loadAccounts() {
        viewModelScope.launch {
            _accountsState.update { ScreenState.Loading }
            try {
                val accounts = getAccountsUseCase.getAccounts()

                if (accounts.isEmpty()) {
                    _accountsState.update { ScreenState.Empty }
                } else {
                    _accountsState.update {
                        ScreenState.Success(
                            result = accounts
                        )
                    }
                }
            } catch (e: Exception) {
                _accountsState.update {
                    ScreenState.Error(
                        e.message ?: ""
                    )
                }
            }
        }
    }
}