package com.example.expense.internal.ui.expense.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.expense.Expense
import com.example.ui.R
import com.example.ui.components.listItems.customListItem.CustomListItem
import com.example.utils.extensions.string.formatWithSpaces
import com.example.utils.extensions.string.isEmoji

@Composable
internal fun ExpenseItem(
    expense: Expense,
    modifier: Modifier = Modifier
) {
    val formattedAmount = expense.amount.formatWithSpaces()

    val trailText = "$formattedAmount ${expense.currency}"
    val trailIcon = ImageVector.vectorResource(R.drawable.ic_more_vert)

    CustomListItem(
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = expense.title
            )
        },
        leadIcon = {
            Text(
                text = expense.leadIcon,
                style = if (expense.leadIcon.isEmoji()) YaFinanceTheme.typography.emoji else YaFinanceTheme.typography.emojiText
            )
        },
        subtitle = expense.subtitle,
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