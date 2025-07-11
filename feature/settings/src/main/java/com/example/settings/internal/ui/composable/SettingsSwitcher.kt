package com.example.settings.internal.ui.composable

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.components.listItems.customListItem.CustomListItem

@Composable
internal fun SettingsSwitcher(
    titleId: Int,
    modifier: Modifier = Modifier
) {
    var isDarkTheme by rememberSaveable { mutableStateOf(false) }

    CustomListItem(
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = stringResource(titleId)
            )
        },
        trailItem = {
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { isDarkTheme = it },
                colors = SwitchDefaults.colors(checkedTrackColor = YaFinanceTheme.colors.primaryBackground)
            )
        },
        modifier = modifier
    )
}