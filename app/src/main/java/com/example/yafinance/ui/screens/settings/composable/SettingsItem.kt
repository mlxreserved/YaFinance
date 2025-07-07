package com.example.yafinance.ui.screens.settings.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem

@Composable
fun SettingsItem(titleId: Int, trailIcon: ImageVector, modifier: Modifier = Modifier) {
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