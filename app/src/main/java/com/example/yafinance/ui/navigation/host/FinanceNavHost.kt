package com.example.yafinance.ui.navigation.host

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.yafinance.ui.screens.account.AccountsScreen
import com.example.yafinance.ui.screens.expense.ExpensesScreen
import com.example.yafinance.ui.screens.categories.CategoriesScreen
import com.example.yafinance.ui.navigation.routes.Routes.AccountsRoute
import com.example.yafinance.ui.navigation.routes.Routes.CategoriesRoute
import com.example.yafinance.ui.navigation.routes.Routes.ExpensesRoute
import com.example.yafinance.ui.navigation.routes.Routes.IncomesRoute
import com.example.yafinance.ui.navigation.routes.Routes.SettingsRoute
import com.example.yafinance.ui.screens.income.IncomesScreen
import com.example.yafinance.ui.screens.settings.SettingsScreen
import com.example.yafinance.ui.utils.mock.AccountMock.account
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
            AccountsScreen(account = account)
        }
        composable<CategoriesRoute> {
            CategoriesScreen(categories = categories)
        }
        composable<SettingsRoute> {
            SettingsScreen(settings = settings)
        }
    }
}

