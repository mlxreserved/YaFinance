package com.example.yafinance.ui.screens.income.composable

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
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.ui.utils.formatWithSpaces

@Composable
fun IncomeSuccess(incomes: List<Income>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val totalAmount by remember {
            mutableStateOf(incomes.sumOf { it.amount.toDouble() }.toString())
        }
        val formattedTotalAmount = totalAmount.formatWithSpaces()
        val trailTotalText = "$formattedTotalAmount ${incomes.first().currency}"

        IncomeTotal(trailTotalText)

        LazyColumn {

            items(items = incomes, key = { it.id }) { income ->
                val formattedAmount = income.amount.formatWithSpaces()
                val trailText = "$formattedAmount ${income.currency}"
                val trailIcon = ImageVector.vectorResource(R.drawable.more_vert)

                IncomeItem(title = income.title, trailText = trailText, trailIcon = trailIcon)
            }
        }
    }

}