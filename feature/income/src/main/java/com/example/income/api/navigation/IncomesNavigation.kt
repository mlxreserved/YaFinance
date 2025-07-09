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
object IncomesHistoryRoute

fun NavController.navigateToIncomesHistory(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = IncomesHistoryRoute) {
        navOptions()
    }
}


fun NavGraphBuilder.incomesHistoryScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit
) {
    composable<IncomesHistoryRoute> {
        IncomesHistoryScreen(
            viewModelFactory = viewModelFactory,
            onLeadIconClick = onLeadIconClick
        )
    }
}

fun NavGraphBuilder.incomesBase(
    viewModelFactory: ViewModelProvider.Factory,
    onHistoryIconClick: () -> Unit,
    historyDestination: NavGraphBuilder.() -> Unit
) {
    navigation<IncomesAllRoutes>(startDestination = IncomesRoute) {
        composable<IncomesRoute> {
            IncomesScreen(
                onHistoryClick = onHistoryIconClick,
                viewModelFactory = viewModelFactory
            )
        }
        historyDestination()
    }
}