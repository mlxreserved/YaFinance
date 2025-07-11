package com.example.account.internal.ui.editAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.account.Account
import com.example.domain.usecase.account.inter.ChangeAccountInfoUseCase
import com.example.domain.usecase.global.inter.SetCurrentAccountNameUseCase
import com.example.domain.usecase.global.inter.SetCurrentBalanceUseCase
import com.example.domain.usecase.global.inter.SetCurrentCurrencyUseCase
import com.example.ui.data.state.ScreenState
import com.example.utils.extensions.string.currencyToString
import com.example.utils.extensions.string.formatWithoutSpaces
import com.example.model.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditAccountViewModel @Inject constructor(
    private val changeAccountInfoUseCase: ChangeAccountInfoUseCase,
    private val setCurrentCurrencyUseCase: SetCurrentCurrencyUseCase,
    private val setCurrentAccountNameUseCase: SetCurrentAccountNameUseCase,
    private val setCurrentBalanceUseCase: SetCurrentBalanceUseCase
) : ViewModel() {
    private val _editAccountState: MutableStateFlow<ScreenState<Account>> =
        MutableStateFlow<ScreenState<Account>>(
            ScreenState.Empty
        )
    val editAccountState: StateFlow<ScreenState<Account>> = _editAccountState.asStateFlow()

    private fun changeAccountInfo(id: Int, name: String, sum: String, currency: String) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)
            val accountRequest = Account(
                id = id,
                name = name,
                sum = sum.formatWithoutSpaces(),
                currency = currency.currencyToString()
            )

            when (
                val response = changeAccountInfoUseCase.changeAccountInfo(
                    id = id,
                    accountRequest = accountRequest
                )
            ) {
                is Result.Error -> updateState(ScreenState.Error(response.error))
                is Result.Success<Account> -> updateState(ScreenState.Success(response.result))
            }
        }
    }

    fun onApplyEditAccountInfo(id: Int, name: String, sum: String, currency: String) {
        changeAccountInfo(id = id, name = name, sum = sum, currency = currency)
    }

    fun setGlobalCurrency(newCurrency: String) {
        setCurrentCurrencyUseCase.setCurrency(newCurrency.currencyToString())
    }

    fun setGlobalAccountName(newAccountName: String) {
        setCurrentAccountNameUseCase.setAccountName(newAccountName)
    }

    fun setGlobalBalance(newBalance: String) {
        setCurrentBalanceUseCase.setBalance(newBalance)
    }

    private fun updateState(state: ScreenState<Account>) {
        _editAccountState.update { state }
    }
}