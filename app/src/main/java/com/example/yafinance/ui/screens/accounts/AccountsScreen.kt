package com.example.yafinance.ui.screens.accounts

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yafinance.R
import com.example.yafinance.ui.screens.accounts.composable.success.AccountSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.SnackbarViewModel
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun AccountsScreen(
    snackbarViewModel: SnackbarViewModel,
    onTrailIconClick: (Account) -> Unit,
    onBalanceClick: (Account) -> Unit,
    accountsViewModel: AccountsViewModel = hiltViewModel()
) {
    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.my_account,
        )
    )

    val accountsState by accountsViewModel.screenState.collectAsStateWithLifecycle()

    when (val state = accountsState) {
        ScreenState.Empty -> {
            EmptyScreen(stringResource(R.string.empty_accounts))
        }

        is ScreenState.Error -> {
            ErrorScreen(screenTitleId = R.string.my_account, text = state.message)
            snackbarViewModel.showMessage(state.message)
        }

        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.my_account)
        }

        is ScreenState.Success -> {
            val account = state.result.first()
            AccountSuccess(
                accounts = state.result, onTrailIconClick = { onTrailIconClick(account) },
                onBalanceClick = { onBalanceClick(account) }
            )
        }
    }
}

