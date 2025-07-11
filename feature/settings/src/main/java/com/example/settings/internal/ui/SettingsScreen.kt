package com.example.settings.internal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.settings.R
import com.example.settings.internal.ui.composable.SettingsItem
import com.example.settings.internal.ui.composable.SettingsSwitcher
import com.example.settings.internal.ui.model.Setting
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner

@Composable
internal fun SettingsScreen(
    isConnected: Boolean,
    settings: List<Setting>,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    titleId = R.string.settings
                )
                NetworkStatusBanner(
                    isConnected = isConnected,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = YaFinanceTheme.colors.primaryBackground)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding())) {
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
}