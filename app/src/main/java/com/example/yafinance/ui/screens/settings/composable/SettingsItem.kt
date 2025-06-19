package com.example.yafinance.ui.screens.settings.composable

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun SettingsItem(titleId: Int, trailIcon: ImageVector) {
    CustomListItem(
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = stringResource(titleId)
            )
        },
        trailItem = { Icon(trailIcon, contentDescription = null) },
        modifier = Modifier.height(56.dp)
    )
}