package com.example.income.internal.ui.incomesHistory.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.income.Income
import com.example.ui.R
import com.example.ui.components.listItems.customListItem.CustomListItem
import com.example.utils.extensions.string.formatWithSpaces
import com.example.utils.extensions.string.isEmoji
import com.example.utils.extensions.string.toDateWithTimeString

@Composable
internal fun IncomeHistoryItem(
    historyItem: Income,
    modifier: Modifier = Modifier
) {
    val formattedAmount = historyItem.amount.formatWithSpaces()

    val trailText = "$formattedAmount ${historyItem.currency}"
    val trailIcon = ImageVector.vectorResource(R.drawable.ic_more_vert)


    CustomListItem(
        leadIcon = {
            Text(
                text = historyItem.leadIcon,
                style = if (historyItem.leadIcon.isEmoji()) YaFinanceTheme.typography.emoji else YaFinanceTheme.typography.emojiText
            )
        },
        title = {
            Text(
                text = historyItem.title,
                style = YaFinanceTheme.typography.title
            )
        },
        subtitle = historyItem.subtitle,
        trailTextItem = {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = trailText,
                    style = YaFinanceTheme.typography.title,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = historyItem.transactionDate.toDateWithTimeString(),
                    style = YaFinanceTheme.typography.title
                )
            }
        },
        trailItem = {
            Icon(
                imageVector = trailIcon,
                contentDescription = null,
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        hasDate = true,
        modifier = modifier
    )
}