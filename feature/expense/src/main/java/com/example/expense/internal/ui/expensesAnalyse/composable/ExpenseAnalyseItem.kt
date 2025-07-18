package com.example.expense.internal.ui.expensesAnalyse.composable

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
import com.example.model.categoryTotal.CategoryTotal
import com.example.ui.R
import com.example.ui.components.listItems.customListItem.CustomListItem
import com.example.utils.extensions.string.formatWithSpaces
import com.example.utils.extensions.string.isEmoji

@Composable
internal fun ExpenseAnalyseItem(
    historyItem: CategoryTotal,
    modifier: Modifier = Modifier
) {
    val formattedAmount = historyItem.totalAmount.toString().formatWithSpaces()

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
                text = historyItem.categoryName,
                style = YaFinanceTheme.typography.title
            )
        },
        trailTextItem = {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = historyItem.percentage,
                    style = YaFinanceTheme.typography.title,
                )
                Text(
                    text = trailText,
                    style = YaFinanceTheme.typography.title,
                    modifier = Modifier.padding(bottom = 4.dp)
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