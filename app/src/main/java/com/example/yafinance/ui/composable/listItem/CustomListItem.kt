package com.example.yafinance.ui.composable.listItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun CustomListItem(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    backgroundContainerColor: Color = YaFinanceTheme.colors.surface,
    backgroundLeadColor: Color = YaFinanceTheme.colors.secondaryBackground,
    leadIcon: String? = null,
    subtitle: String? = null,
    trailText: String? = null,
    trailItem: @Composable (() -> Unit)? = null,
    hasDivider: Boolean = true
) {
    Column {
        ListItem(
            headlineContent = title,
            leadingContent = if (leadIcon != null) {
                { Lead(leadIcon = leadIcon, backgroundColor = backgroundLeadColor) }
            } else null,
            supportingContent = if (subtitle != null) {
                {
                    Text(text = subtitle, style = YaFinanceTheme.typography.subtitle)
                }
            } else null,
            trailingContent = if (trailText != null || trailItem != null) {
                {
                    Trail(
                        trailText = trailText,
                        trailItem = trailItem,

                    )
                }
            } else null,
            colors = ListItemDefaults.colors(containerColor = backgroundContainerColor),
            modifier = modifier
        )

        if(hasDivider)
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}
