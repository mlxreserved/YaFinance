package com.example.income.internal.ui.income.composable

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
import com.example.domain.model.income.Income
import com.example.ui.components.listItems.TotalItem
import com.example.utils.extensions.calculatedSum.calculatedSumAsString
import com.example.utils.extensions.string.formatWithSpaces

@Composable
internal fun IncomeSuccess(
    onEditTransactionClick: (Int) -> Unit,
    incomes: List<Income>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val totalAmount by rememberSaveable {
            mutableStateOf(incomes.calculatedSumAsString { it.amount.toDouble() })
        }
        val formattedTotalAmount = totalAmount.formatWithSpaces()
        val trailTotalText = "$formattedTotalAmount ${incomes.first().currency}"

        TotalItem(
            trailText = trailTotalText,
            modifier = Modifier.height(56.dp)
        )

        LazyColumn {
            items(items = incomes, key = { it.id }) { income ->
                IncomeItem(
                    income = income,
                    modifier = Modifier
                        .height(72.dp)
                        .clickable { onEditTransactionClick(income.id) }
                )
            }
        }
    }

}