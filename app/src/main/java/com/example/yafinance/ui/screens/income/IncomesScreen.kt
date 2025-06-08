package com.example.yafinance.ui.screens.income

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
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.ui.composable.CustomFloatingButton
import com.example.yafinance.ui.screens.income.composable.IncomeItem
import com.example.yafinance.ui.screens.income.composable.IncomeTotal
import com.example.yafinance.ui.utils.Format

@Composable
fun IncomesScreen(
    incomes: List<Income>,
    modifier: Modifier = Modifier
) {
    LazyColumn {

        item {
            val totalAmount = incomes.sumOf { it.amount.toDouble() }.toString()
            val formattedTotalAmount = Format.formatWithSpaces(totalAmount)
            val trailText = "$formattedTotalAmount ${incomes.first().currency}"

            IncomeTotal(trailText)
        }

        items(items = incomes) { income ->
            val formattedAmount = Format.formatWithSpaces(income.amount)
            val trailText = "$formattedAmount ${income.currency}"
            val trailIcon = ImageVector.vectorResource(R.drawable.more_vert)

            IncomeItem(title = income.title, trailText = trailText, trailIcon = trailIcon)
        }
    }
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize().padding(end = 16.dp, bottom = 14.dp)
    ){
        CustomFloatingButton()
    }
}