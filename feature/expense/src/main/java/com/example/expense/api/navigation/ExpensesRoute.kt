package com.example.expense.api.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.expense.internal.ui.ExpensesScreen
import kotlinx.serialization.Serializable

@Serializable
object ExpensesRoute

fun NavGraphBuilder.expenseScreen(
    onTrailIconClick: () -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    composable<ExpensesRoute> {
        ExpensesScreen(onTrailIconClick = onTrailIconClick, viewModelFactory = viewModelFactory)
    }
}