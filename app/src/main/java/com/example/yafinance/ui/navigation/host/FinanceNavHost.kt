package com.example.yafinance.ui.navigation.host

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.yafinance.ui.account.AccountScreen
import com.example.yafinance.ui.expense.ExpensesScreen
import com.example.yafinance.ui.items.ItemsScreen
import com.example.yafinance.ui.navigation.routes.Account
import com.example.yafinance.ui.navigation.routes.Expenses
import com.example.yafinance.ui.navigation.routes.Items
import com.example.yafinance.ui.navigation.routes.Revenue
import com.example.yafinance.ui.navigation.routes.Settings
import com.example.yafinance.ui.revenue.RevenueScreen
import com.example.yafinance.ui.settings.SettingsScreen

@Composable
fun FinanceNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Expenses,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable<Expenses> {
            ExpensesScreen()
        }
        composable<Revenue> {
            RevenueScreen()
        }
        composable<Account> {
            AccountScreen()
        }
        composable<Items> {
            ItemsScreen()
        }
        composable<Settings> {
            SettingsScreen()
        }
    }
}