package com.example.yafinance.ui.navigation.bottomNavBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.Routes.topLevelRoutes

@Composable
fun BottomNavigationBar(navController: NavHostController) {


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        topLevelRoutes.forEach { topLevelRoute ->
            val screenName = stringResource(topLevelRoute.nameId)
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(topLevelRoute.icon),
                        contentDescription = screenName
                    )
                },
                label = { Text(screenName) },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = YaFinanceTheme.colors.primaryBackground,
                    indicatorColor = YaFinanceTheme.colors.secondaryBackground
                ),
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