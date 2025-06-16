package com.example.yafinance.ui.screens.expense

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.yafinance.R
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.ui.navigation.routes.ScreensRoute
import com.example.yafinance.ui.screens.expense.composable.ExpensesSuccess
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun ExpensesScreen(
    expenses: List<Expense>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    TopAppBarStateProvider.update(TopAppBarState(titleId = R.string.expenses_today))

    ExpensesSuccess(expenses = expenses, modifier = modifier, onTrailIconClick = {
        navController.navigate(
            ScreensRoute.HistoryRoute
        )
    })
}