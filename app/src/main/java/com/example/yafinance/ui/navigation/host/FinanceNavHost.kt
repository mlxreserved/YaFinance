package com.example.yafinance.ui.navigation.host

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.EditAccountRoute
import com.example.yafinance.ui.screens.accounts.AccountsScreen
import com.example.yafinance.ui.screens.expense.ExpensesScreen
import com.example.yafinance.ui.screens.categories.CategoriesScreen
import com.example.yafinance.ui.navigation.routes.ScreensRoute.AccountsRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.CategoriesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.IncomesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.SettingsRoute
import com.example.yafinance.ui.screens.accounts.EditAccountScreen
import com.example.yafinance.ui.screens.income.IncomesScreen
import com.example.yafinance.ui.screens.settings.SettingsScreen
import com.example.yafinance.ui.utils.mock.CategoriesMock.categories
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
        startDestination = ExpensesRoute,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable<ExpensesRoute> {
            ExpensesScreen(expenses = expenses)
        }
        composable<IncomesRoute> {
            IncomesScreen(incomes = incomes)
        }
        composable<AccountsRoute> {
            AccountsScreen(navController = navController)
        }
        composable<CategoriesRoute> {
            CategoriesScreen(categories = categories)
        }
        composable<SettingsRoute> {
            SettingsScreen(settings = settings)
        }


        composable<EditAccountRoute> {
            val args = it.toRoute<EditAccountRoute>()

            EditAccountScreen(amount = args.amount, currency = args.currency)
        }
    }
}

