package com.example.yafinance.ui.screens.categories.composable

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.isEmoji

@Composable
fun CategoryItem(category: Category) {
    CustomListItem(
        leadIcon = {
            Text(
                text = category.leadIcon,
                style = if (category.leadIcon.isEmoji()) YaFinanceTheme.typography.emoji else YaFinanceTheme.typography.emojiText
            )
        },
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = category.title
            )
        },
        modifier = Modifier.height(70.dp)
    )
}