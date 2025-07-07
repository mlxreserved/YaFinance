package com.example.yafinance.ui.composable.listItems.customListItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme

@Composable
fun Lead(
    backgroundColor: Color = YaFinanceTheme.colors.secondaryBackground,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null
) {
    if (content != null) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .clip(CircleShape)
                .size(24.dp)
                .background(color = backgroundColor)
        ) {
            content()
        }

    }
}