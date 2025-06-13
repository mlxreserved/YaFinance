package com.example.yafinance.ui.theme.customTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class YaFinanceColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val surface: Color,
    val secondaryBackground: Color,
    val tertiaryBackground: Color,
    val tertiaryText: Color,
    val moreColor: Color,
    val outlineColor: Color
)

data class YaFinanceTypography(
    val header: TextStyle,
    val title: TextStyle,
    val subtitle: TextStyle,
    val emoji: TextStyle,
    val emojiText: TextStyle
)

object YaFinanceTheme {
    val colors: YaFinanceColors
        @Composable @ReadOnlyComposable
        get() = LocalYaFinanceColors.current

    val typography: YaFinanceTypography
        @Composable @ReadOnlyComposable
        get() = LocalYaFinanceTypography.current
}

val LocalYaFinanceColors = staticCompositionLocalOf<YaFinanceColors> {
    error("No colors provided")
}

val LocalYaFinanceTypography = staticCompositionLocalOf<YaFinanceTypography> {
    error("No font provided")
}