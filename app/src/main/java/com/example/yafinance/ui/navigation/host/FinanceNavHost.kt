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
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.expense.api.navigation.ExpensesAllRoutes
import com.example.expense.api.navigation.expensesBase
import com.example.expense.api.navigation.expensesHistoryScreen
import com.example.expense.api.navigation.navigateToExpensesHistory
import com.example.income.api.navigation.incomesBase
import com.example.income.api.navigation.incomesHistoryScreen
import com.example.income.api.navigation.navigateToIncomesHistory
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.navigation.routes.ScreensRoute.AccountsAllRoutes
import com.example.yafinance.ui.navigation.routes.ScreensRoute.EditAccountRoute
import com.example.yafinance.ui.screens.accounts.AccountsScreen
import com.example.yafinance.ui.screens.categories.CategoriesScreen
import com.example.yafinance.ui.navigation.routes.ScreensRoute.AccountsRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.CategoriesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.SettingsRoute
import com.example.yafinance.ui.screens.editAccount.EditAccountScreen
import com.example.yafinance.ui.screens.settings.SettingsScreen
import com.example.yafinance.ui.screens.settings.model.settings
import com.example.yafinance.ui.utils.formatWithoutSpaces

@Composable
fun FinanceNavHost(
    expenseViewModelFactory: ViewModelProvider.Factory,
    incomeViewModelFactory: ViewModelProvider.Factory,
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
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        expensesBase(
            viewModelFactory = expenseViewModelFactory,
            onHistoryIconClick = navController::navigateToExpensesHistory
        ) {
            expensesHistoryScreen(
                viewModelFactory = expenseViewModelFactory,
                onLeadIconClick = navController::navigateUp
            )
        }

        incomesBase(
            viewModelFactory = incomeViewModelFactory,
            onHistoryIconClick = navController::navigateToIncomesHistory
        ) {
            incomesHistoryScreen(
                viewModelFactory = incomeViewModelFactory,
                onLeadIconClick = navController::navigateUp
            )
        }

        navigation<AccountsAllRoutes>(
            startDestination = AccountsRoute
        ) {

            composable<AccountsRoute> {
                AccountsScreen(
                    viewModelFactory = expenseViewModelFactory,
                    onTrailIconClick = { account ->
                        navigateToEditAccountRoute(account = account, navController = navController)
                    }
                )
            }
            composable<EditAccountRoute> {
                val args = it.toRoute<EditAccountRoute>()

                EditAccountScreen(
                    viewModelFactory = expenseViewModelFactory,
                    onLeadIconClick = {
                        navController.navigateUp()
                    },
                    onSuccess = {
                        navController.navigateUp()
                    },
                    currency = args.currency,
                    sum = args.sum,
                    id = args.id,
                    name = args.name
                )
            }
        }

        composable<CategoriesRoute> {
            CategoriesScreen(
                viewModelFactory = expenseViewModelFactory
            )
        }

        composable<SettingsRoute> {
            SettingsScreen(settings = settings)
        }
    }
}

fun navigateToEditAccountRoute(account: Account, navController: NavHostController) {
    navController.navigate(
        EditAccountRoute(
            sum = account.sum.formatWithoutSpaces(),
            currency = account.currency,
            id = account.id,
            name = account.name
        )
    )
}