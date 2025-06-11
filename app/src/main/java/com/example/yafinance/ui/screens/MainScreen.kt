package com.example.yafinance.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.yafinance.ui.composable.floatingButton.CustomFloatingButton
import com.example.yafinance.ui.composable.topAppBar.CustomTopAppBar
import com.example.yafinance.ui.navigation.bottomNavBar.BottomNavigationBar
import com.example.yafinance.ui.navigation.host.FinanceNavHost
import com.example.yafinance.ui.navigation.routes.ScreensRoute.AccountsRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.IncomesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesRoute

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = { CustomTopAppBar() },
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = {
            when(currentRoute) {
                ExpensesRoute.javaClass.canonicalName -> CustomFloatingButton {  }
                IncomesRoute.javaClass.canonicalName -> CustomFloatingButton {  }
                AccountsRoute.javaClass.canonicalName -> CustomFloatingButton {  }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        FinanceNavHost(navController = navController, paddingValues = innerPadding)
    }
}