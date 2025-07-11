package com.example.yafinance.ui.screens.accounts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import com.example.yafinance.R
import com.example.yafinance.ui.screens.accounts.composable.AccountSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.LocalSnackbarViewModel
import com.example.yafinance.domain.models.account.Account
//import com.example.yafinance.ui.LocalSnackbarViewModel
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.utils.toUserMessage

@Composable
fun AccountsScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onTrailIconClick: (Account) -> Unit,
    accountsViewModel: AccountsViewModel = viewModel(factory = viewModelFactory)
) {
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    val accountsState by accountsViewModel.screenState.collectAsStateWithLifecycle()

    when (val state = accountsState) {
        ScreenState.Empty -> {
            EmptyScreen(
                text = stringResource(R.string.empty_accounts),
                screenTitleId = R.string.my_account,
                modifier = Modifier.fillMaxSize()
            )
        }

        is ScreenState.Error -> {
            if (state.isRetried) {
                snackbarViewModel.showMessage(state.message.toUserMessage(context))
            }
            ErrorScreen(
                screenTitleId = R.string.my_account,
                text = state.message.toUserMessage(context),
                onClick = {
                    accountsViewModel.onRetryClicked()
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        ScreenState.Loading -> {
            LoadingScreen(
                screenTitleId = R.string.my_account,
                modifier = Modifier.fillMaxSize()
            )
        }

        is ScreenState.Success -> {
            val account = state.result

            AccountSuccess(
                account = account,
                onTrailIconClick = { onTrailIconClick(account) }
            )
        }
    }
}

