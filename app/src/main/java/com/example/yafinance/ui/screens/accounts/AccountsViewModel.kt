package com.example.yafinance.ui.screens.accounts

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.usecase.inter.GetAccountsUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.BaseViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(private val getAccountsUseCase: GetAccountsUseCase) :
    BaseViewModel<List<Account>>() {

    init {
        loadAccounts()
    }

    private fun loadAccounts(countErrors: Int = 0) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            when(val response = getAccountsUseCase.getAccounts()) {
                is Result.Success -> updateStateBasedOnListContent(response.result)
                is Result.Error -> updateState(ScreenState.Error(message = response.error, countErrors))
            }
        }
    }

    fun onRetryClicked() {
        loadAccounts(countErrors = 1)
    }
}