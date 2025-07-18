package com.example.account.internal.ui.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.account.R
import com.example.account.internal.ui.account.composable.AccountSuccess
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.account.Account
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.components.screens.EmptyScreen
import com.example.ui.components.screens.ErrorScreen
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner
import com.example.ui.data.state.ScreenState
import com.example.ui.extensions.toUserMessage

@Composable
internal fun AccountsScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onEditClick: (Account) -> Unit,
    accountsViewModel: AccountsViewModel = viewModel(factory = viewModelFactory)
) {
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    val accountsState by accountsViewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        accountsViewModel.loadAccounts()
    }

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    trailIconId = R.drawable.ic_edit,
                    titleId = R.string.my_account,
                    onTrailIconClick = {
                        if (accountsState is ScreenState.Success<Account>) {
                            onEditClick((accountsState as ScreenState.Success<Account>).result)
                        } else if (accountsState is ScreenState.Error) {
                            snackbarViewModel.showMessage(
                                (accountsState as ScreenState.Error).message.toUserMessage(context)
                            )
                        }
                    }
                )
                NetworkStatusBanner(
                    isConnected = isConnected,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = YaFinanceTheme.colors.primaryBackground)
                )
            }
        }
    ) { innerPadding ->
        when (val state = accountsState) {
            ScreenState.Empty -> {
                EmptyScreen(
                    text = stringResource(R.string.empty_accounts),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())

                )
            }

            is ScreenState.Error -> {
                if (state.isRetried) {
                    snackbarViewModel.showMessage(state.message.toUserMessage(context))
                }
                ErrorScreen(
                    text = state.message.toUserMessage(context),
                    onClick = {
                        accountsViewModel.onRetryClicked()
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                )
            }

            ScreenState.Loading -> {
                LoadingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                )
            }

            is ScreenState.Success -> {
                val account = state.result

                AccountSuccess(
                    account = account,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                )
            }
        }
    }
}

