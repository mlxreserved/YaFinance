package com.example.yafinance.ui.screens.categories.composable

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun CategoryItem(category: Category) {
    CustomListItem(
        leadIcon = category.leadIcon,
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = category.title
            )
        },
        modifier = Modifier.height(70.dp)
    )
}