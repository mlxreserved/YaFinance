package com.example.yafinance.ui.composable.listItem

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun Trail(
    modifier: Modifier = Modifier,
    trailText: String? = null,
    trailItem: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (trailText != null) {
            Text(
                style = YaFinanceTheme.typography.title,
                text = trailText
            )
        }

        if (trailItem != null) {
            trailItem()
        }
    }
}