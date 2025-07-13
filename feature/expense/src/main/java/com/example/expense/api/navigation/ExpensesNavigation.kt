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
object ExpenseAllHistoryRoute

@Serializable
object ExpensesHistoryRoute

fun NavController.navigateToExpensesHistory(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = ExpensesHistoryRoute) {
        navOptions()
    }
}


fun NavGraphBuilder.expensesHistoryScreen(
    isConnected: Boolean,
    onEditTransactionClick: (Int) -> Unit,
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit,
    editTransactionDestination: NavGraphBuilder.() -> Unit
) {
    navigation<ExpenseAllHistoryRoute>(startDestination = ExpensesHistoryRoute) {
        composable<ExpensesHistoryRoute> {
            ExpensesHistoryScreen(
                isConnected = isConnected,
                onEditTransactionClick = onEditTransactionClick,
                viewModelFactory = viewModelFactory,
                onLeadIconClick = onLeadIconClick
            )
        }

        editTransactionDestination()
    }
}

fun NavGraphBuilder.expensesBase(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onHistoryIconClick: () -> Unit,
    onAddTransactionClick: () -> Unit,
    onEditTransactionClick: (Int) -> Unit,
    historyDestination: NavGraphBuilder.() -> Unit,
    editTransactionDestination: NavGraphBuilder.() -> Unit
) {
    navigation<ExpensesAllRoutes>(startDestination = ExpensesRoute) {
        composable<ExpensesRoute> {
            ExpensesScreen(
                isConnected = isConnected,
                onAddTransactionClick = onAddTransactionClick,
                onEditTransactionClick = onEditTransactionClick,
                onHistoryClick = onHistoryIconClick,
                viewModelFactory = viewModelFactory
            )
        }
        historyDestination()

        editTransactionDestination()
    }
}