package com.example.yafinance.ui.screens.income.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.ui.composable.listItems.TotalItem
import com.example.yafinance.ui.utils.calculatedSumAsString
import com.example.yafinance.ui.utils.formatWithSpaces

@Composable
fun IncomeSuccess(incomes: List<Income>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val totalAmount by rememberSaveable {
            mutableStateOf(incomes.calculatedSumAsString { it.amount.toDouble() })
        }
        val formattedTotalAmount = totalAmount.formatWithSpaces()
        val trailTotalText = "$formattedTotalAmount ${incomes.first().currency}"

        TotalItem(trailTotalText)

        LazyColumn {
            items(items = incomes, key = { it.id }) { income ->
                IncomeItem(income = income)
            }
        }
    }

}