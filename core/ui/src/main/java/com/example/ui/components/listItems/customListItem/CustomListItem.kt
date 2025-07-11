package com.example.ui.components.listItems.customListItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.example.design.theme.customTheme.YaFinanceTheme

@Composable
fun CustomListItem(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    backgroundContainerColor: Color = YaFinanceTheme.colors.surface,
    backgroundLeadColor: Color = YaFinanceTheme.colors.secondaryBackground,
    leadIcon: @Composable (() -> Unit)? = null,
    subtitle: String? = null,
    trailText: String? = null,
    trailTextItem: @Composable (() -> Unit)? = null,
    trailItem: @Composable (() -> Unit)? = null,
    hasDivider: Boolean = true,
    hasDate: Boolean = false
) {
    Column {
        ListItem(
            headlineContent = title,
            leadingContent = if (leadIcon != null) {
                {
                    Lead(content = leadIcon, backgroundColor = backgroundLeadColor)
                }
            } else null,
            supportingContent = if (subtitle != null) {
                {
                    Text(
                        text = subtitle,
                        style = YaFinanceTheme.typography.subtitle,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            } else null,
            trailingContent = if (trailText != null || trailItem != null) {
                {
                    Trail(
                        trailTextItem = trailTextItem,
                        trailText = trailText,
                        trailItem = trailItem,
                        hasDate = hasDate
                    )
                }
            } else null,
            colors = ListItemDefaults.colors(containerColor = backgroundContainerColor),
            modifier = modifier
        )

        if (hasDivider)
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}
