package com.example.yafinance.ui.screens.income.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.yafinance.R
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.utils.formatWithSpaces
import com.example.yafinance.ui.utils.isEmoji

@Composable
fun IncomeItem(
    income: Income,
    modifier: Modifier = Modifier
) {
    val formattedAmount = income.amount.formatWithSpaces()
    val trailText = "$formattedAmount ${income.currency}"
    val trailIcon = ImageVector.vectorResource(R.drawable.ic_more_vert)

    CustomListItem(
        leadIcon = {
            Text(
                text = income.leadIcon,
                style = if (income.leadIcon.isEmoji()) YaFinanceTheme.typography.emoji else YaFinanceTheme.typography.emojiText
            )
        },
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = income.title
            )
        },
        subtitle = income.subtitle,
        trailText = trailText,
        trailItem = {
            Icon(
                imageVector = trailIcon,
                contentDescription = null,
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        modifier = modifier
    )
}