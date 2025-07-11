package com.example.yafinance.ui.navigation.host

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.account.api.navigation.accountBase
import com.example.account.api.navigation.editAccountScreen
import com.example.account.api.navigation.navigateToEditAccount
import com.example.account.internal.ui.editAccount.EditAccountViewModel
import com.example.category.api.navigation.categoryScreen
import com.example.expense.api.navigation.ExpensesAllRoutes
import com.example.expense.api.navigation.expensesBase
import com.example.expense.api.navigation.expensesHistoryScreen
import com.example.expense.api.navigation.navigateToExpensesHistory
import com.example.income.api.navigation.incomesBase
import com.example.income.api.navigation.incomesHistoryScreen
import com.example.income.api.navigation.navigateToIncomesHistory
import com.example.domain.model.account.Account
import com.example.settings.api.navigation.settingsScreen
import com.example.yafinance.ui.navigation.routes.ScreensRoute.EditAccountRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.SettingsRoute
import com.example.yafinance.ui.screens.settings.SettingsScreen
import com.example.yafinance.ui.screens.settings.model.settings
import com.example.yafinance.ui.utils.formatWithoutSpaces

@Composable
fun FinanceNavHost(
    isConnected: Boolean,
    expenseViewModelFactory: ViewModelProvider.Factory,
    incomeViewModelFactory: ViewModelProvider.Factory,
    accountViewModelFactory: ViewModelProvider.Factory,
    categoryViewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        exitTransition = {
            fadeOut(tween(0))
        },
        enterTransition = {
            fadeIn(tween(0))
        },
        popExitTransition = {
            fadeOut(tween(0))
        },
        popEnterTransition = {
            fadeIn(tween(0))
        },
        navController = navController,
        startDestination = ExpensesAllRoutes,
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        expensesBase(
            isConnected = isConnected,
            viewModelFactory = expenseViewModelFactory,
            onHistoryIconClick = navController::navigateToExpensesHistory
        ) {
            expensesHistoryScreen(
                isConnected = isConnected,
                viewModelFactory = expenseViewModelFactory,
                onLeadIconClick = navController::navigateUp
            )
        }

        incomesBase(
            isConnected = isConnected,
            viewModelFactory = incomeViewModelFactory,
            onHistoryIconClick = navController::navigateToIncomesHistory
        ) {
            incomesHistoryScreen(
                isConnected = isConnected,
                viewModelFactory = incomeViewModelFactory,
                onLeadIconClick = navController::navigateUp
            )
        }

        accountBase(
            isConnected = isConnected,
            viewModelFactory = accountViewModelFactory,
            onEditIconClick = { account ->
                navController.navigateToEditAccount(account)
            }
        ) {
            editAccountScreen(
                isConnected = isConnected,
                onSuccess = {
                    navController.navigateUp()
                },
                onLeadIconClick = {
                    navController.navigateUp()
                },
                viewModelFactory = accountViewModelFactory
            )
        }

        categoryScreen(
            isConnected = isConnected,
            viewModelFactory = categoryViewModelFactory
        )

        settingsScreen(
            isConnected = isConnected
        )
    }
}

