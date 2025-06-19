package com.example.yafinance.ui.screens.history.incomesHistory.composable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.utils.formatWithSpaces

@Composable
fun IncomeHistoryItem(historyItem: Income) {

    val formattedAmount = historyItem.amount.formatWithSpaces()

    val trailText = "$formattedAmount ${historyItem.currency}"
    val trailIcon = ImageVector.vectorResource(R.drawable.ic_more_vert)


    CustomListItem(
        leadIcon = historyItem.leadIcon,
        title = { Text(historyItem.title) },
        subtitle = historyItem.subtitle,
        trailText = trailText,
        trailItem = {
            Icon(
                imageVector = trailIcon,
                contentDescription = null,
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        modifier = Modifier.height(72.dp)
    )
}