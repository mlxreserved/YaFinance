package com.example.yafinance.ui.screens.editAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.usecase.inter.ChangeAccountInfoUseCase
import com.example.yafinance.ui.utils.currencyToString
import com.example.yafinance.ui.utils.formatWithoutSpaces
import com.example.yafinance.ui.utils.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAccountViewModel @Inject constructor(private val changeAccountInfoUseCase: ChangeAccountInfoUseCase) :
    ViewModel() {

    private val _editAccountState: MutableStateFlow<ScreenState<Account>> =
        MutableStateFlow<ScreenState<Account>>(
            ScreenState.Empty
        )
    val editAccountState: StateFlow<ScreenState<Account>> = _editAccountState.asStateFlow()

    private fun changeAccountInfo(id: Int, name: String, sum: String, currency: String) {
        viewModelScope.launch {
            _editAccountState.update { ScreenState.Loading }
            try {
                val accountRequest =
                    Account(id = id, name = name, sum = sum.formatWithoutSpaces(), currency.currencyToString())

                val resultAccount =
                    changeAccountInfoUseCase.changeAccountInfo(
                        id = id,
                        accountRequest = accountRequest
                    )

                _editAccountState.update { ScreenState.Success(resultAccount) }
            } catch (e: Exception) {
                _editAccountState.update { ScreenState.Error(e.message ?: "") }
            }
        }
    }

    fun onApplyEditAccountInfo(id: Int, name: String, sum: String, currency: String) {
        changeAccountInfo(id = id, name = name, sum = sum, currency = currency)
    }
}