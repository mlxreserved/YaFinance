package com.example.yafinance.ui.screens.expense.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.ui.composable.listItems.TotalItem
import com.example.yafinance.ui.utils.formatWithSpaces

@Composable
fun ExpensesSuccess(
    expenses: List<Expense>,
    modifier: Modifier = Modifier
) {

    val totalAmount by remember {
        mutableStateOf(expenses.sumOf { it.amount.toDouble() }.toString())
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