package com.example.yafinance.ui.screens.editAccount

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.usecase.inter.ChangeAccountInfoUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.BaseViewModel
import com.example.yafinance.ui.utils.currencyToString
import com.example.yafinance.ui.utils.formatWithoutSpaces
import com.example.yafinance.ui.utils.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAccountViewModel @Inject constructor(private val changeAccountInfoUseCase: ChangeAccountInfoUseCase) :
    BaseViewModel<Account>() {

    override val _screenState: MutableStateFlow<ScreenState<Account>> =
        MutableStateFlow<ScreenState<Account>>(
            ScreenState.Empty
        )
    override val screenState: StateFlow<ScreenState<Account>> = _screenState.asStateFlow()


    private fun changeAccountInfo(id: Int, name: String, sum: String, currency: String) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)
            val accountRequest =
                Account(
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
}