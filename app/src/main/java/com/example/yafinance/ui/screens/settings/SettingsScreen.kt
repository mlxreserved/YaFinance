package com.example.yafinance.ui.screens.settings

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.yafinance.R
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.screens.settings.composable.SettingsItem
import com.example.yafinance.ui.screens.settings.composable.SettingsSwitcher
import com.example.yafinance.ui.screens.settings.model.Setting
import com.example.yafinance.ui.utils.state.TopAppBarState

@Composable
fun SettingsScreen(settings: List<Setting>) {
    val topAppBarViewModel = LocalTopAppBarViewModel.current

    LaunchedEffect(Unit) {
        topAppBarViewModel.update(TopAppBarState(titleId = R.string.settings))
    }

    LazyColumn {
        items(items = settings, key = { it.titleId }) { setting ->
            if (setting.trailId == null) {
                SettingsSwitcher(setting.titleId)
            } else {
                val trailIcon = ImageVector.vectorResource(setting.trailId)

                SettingsItem(titleId = setting.titleId, trailIcon = trailIcon)
            }
        }
    }
}