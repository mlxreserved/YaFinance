package com.example.category.internal.ui.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.category.Category
import com.example.ui.components.listItems.customListItem.CustomListItem
import com.example.utils.extensions.string.isEmoji

@Composable
internal fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier
) {
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
        modifier = modifier
    )
}