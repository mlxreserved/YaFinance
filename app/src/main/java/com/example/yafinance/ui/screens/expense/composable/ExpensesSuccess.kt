package com.example.yafinance.ui.screens.expense.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.yafinance.R
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.ui.utils.formatWithSpaces
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun ExpensesSuccess(
    expenses: List<Expense>,
    modifier: Modifier = Modifier,
    onTrailIconClick: () -> Unit
) {

    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.expenses_today,
            trailId = R.drawable.ic_history,
            onTrailIconClick = onTrailIconClick
        )
    )

    val totalAmount by remember {
        mutableStateOf(expenses.sumOf { it.amount.toDouble() }.toString())
    }
    val formattedTotalAmount = totalAmount.formatWithSpaces()
    val trailTotalText = "$formattedTotalAmount ${expenses.first().currency}"

    Column {

        ExpenseTotal(trailText = trailTotalText)

        LazyColumn(modifier = modifier) {

            items(items = expenses, key = { it.id }) { expense ->
                val formattedAmount = expense.amount.formatWithSpaces()

                val trailText = "$formattedAmount ${expense.currency}"
                val trailIcon = ImageVector.vectorResource(R.drawable.more_vert)

                ExpenseItem(
                    title = expense.title,
                    leadIcon = expense.leadIcon,
                    subtitle = expense.subtitle,
                    trailText = trailText,
                    trailIcon = trailIcon
                )
            }
        }
    }

}