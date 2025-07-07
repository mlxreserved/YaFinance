package com.example.yafinance.ui.screens.settings

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
//import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.screens.settings.composable.SettingsItem
import com.example.yafinance.ui.screens.settings.composable.SettingsSwitcher
import com.example.yafinance.ui.screens.settings.model.Setting

@Composable
fun SettingsScreen(
    settings: List<Setting>,
    modifier: Modifier = Modifier
) {
//    val topAppBarViewModel = LocalTopAppBarViewModel.current

//    LaunchedEffect(Unit) {
//        topAppBarViewModel.update(TopAppBarState(titleId = R.string.settings))
//    }

    LazyColumn {
        items(items = settings, key = { it.titleId }) { setting ->
            if (setting.trailId == null) {
                SettingsSwitcher(
                    titleId = setting.titleId,
                    modifier = Modifier.height(56.dp)
                )
            } else {
                val trailIcon = ImageVector.vectorResource(setting.trailId)

                SettingsItem(
                    titleId = setting.titleId,
                    trailIcon = trailIcon,
                    modifier = Modifier.height(56.dp)
                )
            }
        }
    }
}