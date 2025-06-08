package com.example.yafinance.ui.screens.settings

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.ui.composable.CustomListItem
import com.example.yafinance.ui.screens.settings.model.Setting
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun SettingsScreen(settings: List<Setting>, modifier: Modifier = Modifier) {

    LazyColumn {
        items(items = settings) { setting ->
            if (setting.trailId == null) {
                CustomListItem(
                    title = {
                        Text(
                            style = YaFinanceTheme.typography.title,
                            text = stringResource(setting.titleId)
                        )
                    },
                    trailItem = { Switch(checked = false, onCheckedChange = {}) },
                    modifier = Modifier.height(56.dp)
                )
            } else {
                val trailIcon = ImageVector.vectorResource(setting.trailId)

                CustomListItem(
                    title = {
                        Text(
                            style = YaFinanceTheme.typography.title,
                            text = stringResource(setting.titleId)
                        )
                    },
                    trailItem = { Icon(trailIcon, contentDescription = null) },
                    modifier = Modifier.height(56.dp)
                )
            }
        }
    }
}