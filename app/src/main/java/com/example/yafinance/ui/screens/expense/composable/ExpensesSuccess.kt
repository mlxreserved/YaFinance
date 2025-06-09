package com.example.yafinance.ui.screens.expense.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.ui.composable.floatingButton.CustomFloatingButton
import com.example.yafinance.ui.utils.Format

@Composable
fun ExpensesSuccess(expenses: List<Expense>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {

        item {
            val totalAmount = expenses.sumOf { it.amount.toDouble() }
            val formattedTotalAmount = Format.formatWithSpaces(totalAmount.toString())
            val trailText = "$formattedTotalAmount ${expenses.first().currency}"

            ExpenseTotal(trailText = trailText)

        }
        items(items = expenses) { expense ->
            val formattedAmount = Format.formatWithSpaces(expense.amount)

            val trailText = "$formattedAmount ${expense.currency}"
            val trailIcon = ImageVector.vectorResource(R.drawable.more_vert)

            ExpenseItem(
                title = expense.title,
                leadIcon = expense.leadIcon,
                trailText = trailText,
                trailIcon = trailIcon
            )
        }
    }
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize().padding(end = 16.dp, bottom = 14.dp)
    ){
        CustomFloatingButton()
    }
}