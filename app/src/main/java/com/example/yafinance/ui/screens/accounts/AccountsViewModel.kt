package com.example.yafinance.ui.screens.accounts

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.usecase.inter.GetAccountUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.BaseViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(private val getAccountUseCase: GetAccountUseCase) :
    BaseViewModel<Account>() {

    init {
        loadAccounts()
    }

    private fun loadAccounts(isRetried: Boolean = false) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            when(val response = getAccountUseCase.getAccounts()) {
                is Result.Success -> updateState(ScreenState.Success(result = response.result))
                is Result.Error -> updateState(ScreenState.Error(message = response.error, isRetried))
            }
        }
    }

    fun onRetryClicked() {
        loadAccounts(isRetried = true)
    }
}