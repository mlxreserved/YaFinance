package com.example.yafinance.ui.theme.customTheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> baseDarkPalette
        false -> baseLightPalette
    }

    val typography = YaFinanceTypography(
        header = TextStyle(
            color = colors.primaryText,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp
        ),
        title = TextStyle(
            color = colors.primaryText,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        ),
        subtitle = TextStyle(
            color = colors.tertiaryText,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        emoji = TextStyle(
            fontSize = 18.sp
        ),
        emojiText = TextStyle(
            color = colors.primaryText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    )

    CompositionLocalProvider(
        LocalYaFinanceColors provides colors,
        LocalYaFinanceTypography provides typography,
        content = content
    )

}