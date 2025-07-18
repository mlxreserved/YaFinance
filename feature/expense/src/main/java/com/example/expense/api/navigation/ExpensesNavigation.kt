package com.example.expense.api.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.expense.internal.ui.expense.ExpensesScreen
import com.example.expense.internal.ui.expensesAnalyse.ExpensesAnalyseScreen
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

@Serializable
object ExpensesAnalyseRoute

fun NavController.navigateToExpensesHistory(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = ExpensesHistoryRoute) {
        navOptions()
    }
}

fun NavController.navigateToExpensesAnalyse(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = ExpensesAnalyseRoute) {
        navOptions()
    }
}

fun NavGraphBuilder.expensesAnalyseScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit
) {
    composable<ExpensesAnalyseRoute> {
        ExpensesAnalyseScreen(
            isConnected = isConnected,
            viewModelFactory = viewModelFactory,
            onLeadIconClick = onLeadIconClick
        )
    }
}

fun NavGraphBuilder.expensesHistoryScreen(
    isConnected: Boolean,
    onEditTransactionClick: (Int) -> Unit,
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit,
    onTrailIconClick: () -> Unit,
    editTransactionDestination: NavGraphBuilder.() -> Unit,
    expenseAnalyseDestination: NavGraphBuilder.() -> Unit
) {
    navigation<ExpenseAllHistoryRoute>(startDestination = ExpensesHistoryRoute) {
        composable<ExpensesHistoryRoute> {
            ExpensesHistoryScreen(
                isConnected = isConnected,
                onEditTransactionClick = onEditTransactionClick,
                viewModelFactory = viewModelFactory,
                onLeadIconClick = onLeadIconClick,
                onTrailIconClick = onTrailIconClick
            )
        }

        editTransactionDestination()

        expenseAnalyseDestination()
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