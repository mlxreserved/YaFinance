package com.example.yafinance.ui.screens.account

import androidx.compose.runtime.Composable
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.screens.account.composable.AccountSuccess
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun AccountsScreen(account: Account) {
    TopAppBarStateProvider.update(TopAppBarState(titleId = R.string.my_account, trailId = R.drawable.ic_edit))

    AccountSuccess(account)
}