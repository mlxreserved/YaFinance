package com.example.income.api.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.income.internal.ui.income.IncomesScreen
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


fun NavController.navigateToIncomesHistory(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = IncomesHistoryRoute) {
        navOptions()
    }
}


fun NavGraphBuilder.incomesHistoryBase(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onEditTransactionClick: (Int) -> Unit,
    onLeadIconClick: () -> Unit,
    editTransactionDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<IncomeHistoryAllRoutes>(startDestination = IncomesHistoryRoute) {
        composable<IncomesHistoryRoute> {
            IncomesHistoryScreen(
                isConnected = isConnected,
                viewModelFactory = viewModelFactory,
                onEditTransactionClick = onEditTransactionClick,
                onLeadIconClick = onLeadIconClick
            )
        }
        editTransactionDestination()
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