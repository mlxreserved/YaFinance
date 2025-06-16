package com.example.yafinance.ui.screens.accounts

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yafinance.R
import com.example.yafinance.ui.screens.accounts.composable.success.AccountSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.navigation.routes.ScreensRoute.EditAccountRoute
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun AccountsScreen(
    navController: NavHostController,
    accountsViewModel: AccountsViewModel = hiltViewModel()
) {
    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.my_account,
        )
    )

    val accountsState by accountsViewModel.accountsState.collectAsStateWithLifecycle()

    when (val state = accountsState) {
        ScreenState.Empty -> {
            EmptyScreen("Empty screen")
        }

        is ScreenState.Error -> {
            ErrorScreen(state.message)
        }

        ScreenState.Loading -> {
            LoadingScreen()
        }

        is ScreenState.Success -> {
            val account = state.result.first()
            AccountSuccess(
                accounts = state.result, onTrailIconClick = {
                    navigateToEditAccountRoute(account = account, navController = navController)
                },
                onBalanceClick = {
                    navigateToEditAccountRoute(account = account, navController = navController)
                }
            )
        }
    }
}

fun navigateToEditAccountRoute(account: Account, navController: NavHostController) {
    navController.navigate(
        EditAccountRoute(
            amount = account.sum,
            currency = account.currency,
            id = account.id,
            name = account.name
        )
    )
}