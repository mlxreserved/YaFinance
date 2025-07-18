package com.example.account.internal.ui.account

import androidx.lifecycle.viewModelScope
import com.example.domain.model.account.Account
import com.example.domain.usecase.account.inter.GetAccountUseCase
import com.example.ui.baseViewModel.BaseViewModel
import com.example.ui.data.state.ScreenState
import kotlinx.coroutines.launch
import com.example.model.result.Result
import javax.inject.Inject

class AccountsViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase
) : BaseViewModel<Account>() {

    fun loadAccounts(isRetried: Boolean = false) {
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

    fun onRetryClicked() {
        loadAccounts(isRetried = true)
    }
}