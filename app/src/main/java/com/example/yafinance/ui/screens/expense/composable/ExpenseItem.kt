package com.example.yafinance.ui.screens.expense.composable

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
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.formatWithSpaces

@Composable
fun ExpenseItem(
    expense: Expense
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
        leadIcon = expense.leadIcon,
        subtitle = expense.subtitle,
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