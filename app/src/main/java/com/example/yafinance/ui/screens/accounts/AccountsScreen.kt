package com.example.yafinance.ui.screens.accounts

import androidx.compose.runtime.Composable
import com.example.yafinance.R
import com.example.yafinance.ui.screens.accounts.composable.success.AccountSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.SnackbarViewModel
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider
import com.example.yafinance.ui.utils.toUserMessage

@Composable
fun AccountsScreen(
    viewModelFactory: ViewModelProvider.Factory,
    snackbarViewModel: SnackbarViewModel,
    onTrailIconClick: (Account) -> Unit,
    onBalanceClick: (Account) -> Unit,
    accountsViewModel: AccountsViewModel = viewModel(factory = viewModelFactory)
) {
    val context = LocalContext.current

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
            if(state.isRetried) {
                snackbarViewModel.showMessage(state.message.toUserMessage(context))
            }
            ErrorScreen(
                screenTitleId = R.string.my_account,
                text = state.message.toUserMessage(context),
                onClick = {
                    accountsViewModel.onRetryClicked()
                }
            )
        }

        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.my_account)
        }

        is ScreenState.Success -> {
            val account = state.result
            AccountSuccess(
                account = account, onTrailIconClick = { onTrailIconClick(account) },
                onBalanceClick = { onBalanceClick(account) }
            )
        }
    }
}

