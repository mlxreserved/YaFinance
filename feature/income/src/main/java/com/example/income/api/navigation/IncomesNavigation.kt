package com.example.income.api.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.income.internal.ui.income.IncomesScreen
import com.example.income.internal.ui.incomesAnalyse.IncomeAnalyseScreen
import com.example.income.internal.ui.incomesHistory.IncomesHistoryScreen
import kotlinx.serialization.Serializable

@Serializable
object IncomesAllRoutes

@Serializable
object IncomesRoute

@Serializable
object IncomeHistoryAllRoutes

@Serializable
object IncomesHistoryRoute

@Serializable
object IncomesAnalyseRoute


fun NavController.navigateToIncomesHistory(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = IncomesHistoryRoute) {
        navOptions()
    }
}

fun NavController.navigateToIncomesAnalyse(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = IncomesAnalyseRoute) {
        navOptions()
    }
}

fun NavGraphBuilder.incomesAnalyseScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit
) {
    composable<IncomesAnalyseRoute> {
        IncomeAnalyseScreen(
            isConnected = isConnected,
            viewModelFactory = viewModelFactory,
            onLeadIconClick = onLeadIconClick
        )
    }
}

fun NavGraphBuilder.incomesHistoryBase(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onEditTransactionClick: (Int) -> Unit,
    onLeadIconClick: () -> Unit,
    onTrailIconClick: () -> Unit,
    editTransactionDestination: NavGraphBuilder.() -> Unit,
    incomeAnalyseDestination: NavGraphBuilder.() -> Unit
) {
    navigation<IncomeHistoryAllRoutes>(startDestination = IncomesHistoryRoute) {
        composable<IncomesHistoryRoute> {
            IncomesHistoryScreen(
                isConnected = isConnected,
                viewModelFactory = viewModelFactory,
                onEditTransactionClick = onEditTransactionClick,
                onLeadIconClick = onLeadIconClick,
                onTrailIconClick = onTrailIconClick
            )
        }
        editTransactionDestination()

        incomeAnalyseDestination()
    }

}

fun NavGraphBuilder.incomesBase(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onHistoryIconClick: () -> Unit,
    onAddTransactionClick: () -> Unit,
    onEditTransactionClick: (Int) -> Unit,
    historyDestination: NavGraphBuilder.() -> Unit,
    editTransactionDestination: NavGraphBuilder.() -> Unit
) {
    navigation<IncomesAllRoutes>(startDestination = IncomesRoute) {
        composable<IncomesRoute> {
            IncomesScreen(
                isConnected = isConnected,
                onHistoryClick = onHistoryIconClick,
                onEditTransactionClick = onEditTransactionClick,
                onAddTransactionClick = onAddTransactionClick,
                viewModelFactory = viewModelFactory
            )
        }
        historyDestination()

        editTransactionDestination()
    }
}