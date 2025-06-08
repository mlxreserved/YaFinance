package com.example.yafinance.ui.screens.expense.composable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.yafinance.ui.composable.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun ExpenseItem(
    title: String,
    leadIcon: String,
    subtitle: String? = null,
    trailText: String,
    trailIcon: ImageVector
) {

    CustomListItem(
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = title
            )
        },
        leadIcon = leadIcon,
        subtitle = subtitle,
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