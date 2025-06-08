package com.example.yafinance.ui.utils

import com.example.yafinance.R
import com.example.yafinance.ui.screens.settings.model.Setting

object Settings {
    val settings = listOf<Setting>(
        Setting(titleId = R.string.select_theme, trailId = null),
        Setting(titleId = R.string.primary_color, trailId = R.drawable.arrow_right),
        Setting(titleId = R.string.sounds, trailId = R.drawable.arrow_right),
        Setting(titleId = R.string.haptics, trailId = R.drawable.arrow_right),
        Setting(titleId = R.string.code_password, trailId = R.drawable.arrow_right),
        Setting(titleId = R.string.synchronization, trailId = R.drawable.arrow_right),
        Setting(titleId = R.string.language, trailId = R.drawable.arrow_right),
        Setting(titleId = R.string.about, trailId = R.drawable.arrow_right),
    )
}