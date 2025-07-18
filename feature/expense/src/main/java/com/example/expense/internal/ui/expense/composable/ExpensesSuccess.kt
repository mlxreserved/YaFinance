package com.example.expense.internal.ui.expense.composable

import androidx.compose.foundation.clickable
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
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.expense.Expense
import com.example.ui.components.listItems.TotalItem
import com.example.utils.extensions.calculatedSum.calculatedSumAsString
import com.example.utils.extensions.string.formatWithSpaces


// TODO: Сделать top bar для каждого экрана свой

@Composable
internal fun ExpensesSuccess(
    onEditTransactionClick: (Int) -> Unit,
    expenses: List<Expense>,
    modifier: Modifier = Modifier
) {
    val totalAmount by rememberSaveable {
        mutableStateOf(
            expenses.calculatedSumAsString { it.amount.toDouble() }
        )
    }
    val formattedTotalAmount = totalAmount.formatWithSpaces()
    val trailTotalText = "$formattedTotalAmount ${expenses.first().currency}"

    Column(
        modifier = modifier
    ) {
        TotalItem(
            trailText = trailTotalText,
            modifier = Modifier.height(56.dp),
            backgroundColor = YaFinanceTheme.colors.secondaryBackground
        )

        LazyColumn {
            items(items = expenses, key = { it.localId }) { expense ->
                ExpenseItem(
                    expense = expense,
                    modifier = Modifier.height(72.dp)
                        .clickable{ onEditTransactionClick(expense.localId) }
                )
            }
        }
    }
}