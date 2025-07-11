package com.example.settings.internal.ui.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.components.listItems.customListItem.CustomListItem

@Composable
internal fun SettingsItem(titleId: Int, trailIcon: ImageVector, modifier: Modifier = Modifier) {
    CustomListItem(
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = stringResource(titleId)
            )
        },
        trailItem = { Icon(trailIcon, contentDescription = null) },
        modifier = modifier
    )
}