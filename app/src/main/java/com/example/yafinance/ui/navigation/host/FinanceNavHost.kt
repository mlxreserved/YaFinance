package com.example.yafinance.ui.navigation.host

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.AccountsAllRoutes
import com.example.yafinance.ui.navigation.routes.ScreensRoute.HistoryRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.EditAccountRoute
import com.example.yafinance.ui.screens.accounts.AccountsScreen
import com.example.yafinance.ui.screens.expense.ExpensesScreen
import com.example.yafinance.ui.screens.categories.CategoriesScreen
import com.example.yafinance.ui.navigation.routes.ScreensRoute.AccountsRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.CategoriesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesAllRoutes
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.IncomesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.SettingsRoute
import com.example.yafinance.ui.screens.editAccount.EditAccountScreen
import com.example.yafinance.ui.screens.history.HistoryScreen
import com.example.yafinance.ui.screens.income.IncomesScreen
import com.example.yafinance.ui.screens.settings.SettingsScreen
import com.example.yafinance.ui.utils.mock.ExpensesMock.expenses
import com.example.yafinance.ui.utils.mock.IncomesMock.incomes
import com.example.yafinance.ui.utils.Settings.settings

@Composable
fun FinanceNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = ExpensesAllRoutes,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        navigation<ExpensesAllRoutes>(
            startDestination = ExpensesRoute
        ) {
            composable<ExpensesRoute> {
                ExpensesScreen(
                    expenses = expenses,
                    navController = navController
                )
            }
            composable<HistoryRoute> {
                HistoryScreen()
            }
        }
        composable<IncomesRoute> {
            IncomesScreen(incomes = incomes)
        }
        navigation<AccountsAllRoutes>(startDestination = AccountsRoute) {

            composable<AccountsRoute> {
                AccountsScreen(navController = navController)
            }
            composable<EditAccountRoute> {
                val args = it.toRoute<EditAccountRoute>()

                EditAccountScreen(
                    navController = navController,
                    currency = args.currency,
                    sum = args.amount,
                    id = args.id,
                    name = args.name
                )
            }
        }
        composable<CategoriesRoute> {
            CategoriesScreen()
        }
        composable<SettingsRoute> {
            SettingsScreen(settings = settings)
        }
    }
}

