package com.example.yafinance.ui.screens.expense.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.domain.models.expense.Expense
//import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.composable.listItems.TotalItem
import com.example.yafinance.ui.utils.calculatedSumAsString
import com.example.yafinance.ui.utils.formatWithSpaces

@Composable
fun ExpensesSuccess(
    expenses: List<Expense>,
    onTrailIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
//    val topAppBarViewModel = LocalTopAppBarViewModel.current

    val totalAmount by rememberSaveable {
        mutableStateOf(
            expenses.calculatedSumAsString { it.amount.toDouble() }
        )
    }
    val formattedTotalAmount = totalAmount.formatWithSpaces()
    val trailTotalText = "$formattedTotalAmount ${expenses.first().currency}"

//    LaunchedEffect(Unit) {
//        topAppBarViewModel.update(
//            TopAppBarState(
//                titleId = R.string.expenses_today,
//                trailId = R.drawable.ic_history,
//                onTrailIconClick = onTrailIconClick
//            )
//        )
//    }

    Column(
        modifier = modifier
    ) {
        TotalItem(
            trailText = trailTotalText,
            modifier = Modifier.height(56.dp)
        )

        LazyColumn {

            items(items = expenses, key = { it.id }) { expense ->
                ExpenseItem(
                    expense = expense,
                    modifier = Modifier.height(72.dp)
                )
            }
        }
    }
}