package com.example.expense.api.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.expense.internal.ui.expense.ExpensesScreen
import com.example.expense.internal.ui.expensesHistory.ExpensesHistoryScreen
import kotlinx.serialization.Serializable

@Serializable
object ExpensesAllRoutes

@Serializable
object ExpensesRoute

@Serializable
object ExpensesHistoryRoute

fun NavController.navigateToExpensesHistory(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = ExpensesHistoryRoute) {
        navOptions()
    }
}


fun NavGraphBuilder.expensesHistoryScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit
) {
    composable<ExpensesHistoryRoute> {
        ExpensesHistoryScreen(
            viewModelFactory = viewModelFactory,
            onLeadIconClick = onLeadIconClick
        )
    }
}

fun NavGraphBuilder.expensesBase(
    viewModelFactory: ViewModelProvider.Factory,
    onHistoryIconClick: () -> Unit,
    historyDestination: NavGraphBuilder.() -> Unit
) {
    navigation<ExpensesAllRoutes>(startDestination = ExpensesRoute) {
        composable<ExpensesRoute> {
            ExpensesScreen(
                onHistoryClick = onHistoryIconClick,
                viewModelFactory = viewModelFactory
            )
        }
        historyDestination()
    }
}