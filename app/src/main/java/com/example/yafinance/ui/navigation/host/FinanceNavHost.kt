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
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesHistoryRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.IncomesHistoryRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.AccountsAllRoutes
import com.example.yafinance.ui.navigation.routes.ScreensRoute.EditAccountRoute
import com.example.yafinance.ui.screens.accounts.AccountsScreen
import com.example.yafinance.ui.screens.expense.ExpensesScreen
import com.example.yafinance.ui.screens.categories.CategoriesScreen
import com.example.yafinance.ui.navigation.routes.ScreensRoute.AccountsRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.CategoriesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesAllRoutes
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.IncomesAllRoutes
import com.example.yafinance.ui.navigation.routes.ScreensRoute.IncomesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.SettingsRoute
import com.example.yafinance.ui.screens.editAccount.EditAccountScreen
import com.example.yafinance.ui.screens.history.expensesHistory.ExpensesHistoryScreen
import com.example.yafinance.ui.screens.history.incomesHistory.IncomesHistoryScreen
import com.example.yafinance.ui.screens.income.IncomesScreen
import com.example.yafinance.ui.screens.settings.SettingsScreen
import com.example.yafinance.ui.screens.settings.model.settings

@Composable
fun FinanceNavHost(
    viewModelFactory: ViewModelProvider.Factory,
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
        navigation<ExpensesAllRoutes>(
            startDestination = ExpensesRoute
        ) {
            composable<ExpensesRoute> {
                ExpensesScreen(
                    viewModelFactory = viewModelFactory,
                    onTrailIconClick = { navController.navigate(ExpensesHistoryRoute) }
                )
            }
            composable<ExpensesHistoryRoute> {
                ExpensesHistoryScreen(
                    viewModelFactory = viewModelFactory,
                    onLeadIconClick = { navController.navigateUp() }
                )
            }
        }

        navigation<IncomesAllRoutes>(
            startDestination = IncomesRoute
        ) {
            composable<IncomesRoute> {
                IncomesScreen(
                    viewModelFactory = viewModelFactory,
                    onTrailIconClick = { navController.navigate(IncomesHistoryRoute) }
                )
            }
            composable<IncomesHistoryRoute> {
                IncomesHistoryScreen(
                    viewModelFactory = viewModelFactory,
                    onLeadIconClick = { navController.navigateUp() }
                )
            }
        }

        navigation<AccountsAllRoutes>(
            startDestination = AccountsRoute
        ) {

            composable<AccountsRoute> {
                AccountsScreen(
                    viewModelFactory = viewModelFactory,
                    onTrailIconClick = { account ->
                        navigateToEditAccountRoute(account = account, navController = navController)
                    },
                    onBalanceClick = { account ->
                        navigateToEditAccountRoute(account = account, navController = navController)
                    }
                )
            }
            composable<EditAccountRoute> {
                val args = it.toRoute<EditAccountRoute>()

                EditAccountScreen(
                    viewModelFactory = viewModelFactory,
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
                viewModelFactory = viewModelFactory
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
            sum = account.sum,
            currency = account.currency,
            id = account.id,
            name = account.name
        )
    )
}