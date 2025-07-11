package com.example.settings.api.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.settings.internal.ui.SettingsScreen
import com.example.settings.internal.ui.model.settings
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

fun NavGraphBuilder.settingsScreen(
    isConnected: Boolean
) {
    composable<SettingsRoute> {
        SettingsScreen(
            isConnected = isConnected,
            settings = settings
        )
    }
}