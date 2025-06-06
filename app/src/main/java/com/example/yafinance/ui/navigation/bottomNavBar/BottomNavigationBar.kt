package com.example.yafinance.ui.navigation.bottomNavBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.yafinance.ui.navigation.host.FinanceNavHost
import com.example.yafinance.ui.navigation.routes.Account
import com.example.yafinance.ui.navigation.routes.Expenses
import com.example.yafinance.ui.navigation.routes.Items
import com.example.yafinance.ui.navigation.routes.Revenue
import com.example.yafinance.ui.navigation.routes.Settings
import com.example.yafinance.ui.navigation.routes.TopLevelRoute
import kotlin.contracts.contract

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val topLevelRoutes = listOf(
        TopLevelRoute("Expenses", Expenses, Icons.Filled.KeyboardArrowDown),
        TopLevelRoute("Revenue", Revenue, Icons.Filled.KeyboardArrowUp),
        TopLevelRoute("Account", Account, Icons.Filled.AccountCircle),
        TopLevelRoute("Items", Items, Icons.Filled.DateRange),
        TopLevelRoute("Settings", Settings, Icons.Filled.Settings)
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        topLevelRoutes.forEach { topLevelRoute ->
            BottomNavigationItem(
                icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) },
                label = { Text(topLevelRoute.name) },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}