package com.example.yafinance.ui.screens.categories.composable

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yafinance.ui.composable.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun CategoryItem(leadIcon: String, title: String) {
    CustomListItem(
        leadIcon = leadIcon,
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = title
            )
        },
        modifier = Modifier.height(70.dp)
    )
}