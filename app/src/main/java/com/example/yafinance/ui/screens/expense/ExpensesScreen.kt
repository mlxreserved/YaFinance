package com.example.yafinance.ui.screens.expense

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.yafinance.R
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.ui.screens.expense.composable.ExpensesSuccess
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun ExpensesScreen(
    expenses: List<Expense>,
    modifier: Modifier = Modifier
) {
    TopAppBarStateProvider.update(TopAppBarState(titleId = R.string.expenses_today, trailId = R.drawable.ic_history))

    ExpensesSuccess(expenses = expenses, modifier = modifier)
}