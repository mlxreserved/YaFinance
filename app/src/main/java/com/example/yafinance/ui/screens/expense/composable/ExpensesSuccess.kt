package com.example.yafinance.ui.screens.expense.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.yafinance.R
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.composable.listItems.TotalItem
import com.example.yafinance.ui.utils.calculatedSumAsString
import com.example.yafinance.ui.utils.formatWithSpaces
import com.example.yafinance.ui.utils.state.TopAppBarState

@Composable
fun ExpensesSuccess(
    expenses: List<Expense>,
    onTrailIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topAppBarViewModel = LocalTopAppBarViewModel.current

    topAppBarViewModel.update(
        TopAppBarState(
            titleId = R.string.expenses_today,
            trailId = R.drawable.ic_history,
            onTrailIconClick = onTrailIconClick
        )
    )

    val totalAmount by rememberSaveable {
        mutableStateOf(
            expenses.calculatedSumAsString { it.amount.toDouble() }
        )
    }
    val formattedTotalAmount = totalAmount.formatWithSpaces()
    val trailTotalText = "$formattedTotalAmount ${expenses.first().currency}"

    Column {
        TotalItem(trailText = trailTotalText)

        LazyColumn(modifier = modifier) {

            items(items = expenses, key = { it.id }) { expense ->
                ExpenseItem(
                    expense = expense
                )
            }
        }
    }
}