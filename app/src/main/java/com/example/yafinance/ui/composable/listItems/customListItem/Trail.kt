package com.example.yafinance.ui.composable.listItems.customListItem

import androidx.compose.foundation.layout.Arrangement
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
    trailTextItem: @Composable (() -> Unit)? = null,
    trailItem: @Composable (() -> Unit)? = null,
    hasDate: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        if(hasDate) {
            trailTextItem?.invoke()
        } else {
            if (trailText != null) {
                Text(
                    style = YaFinanceTheme.typography.title,
                    text = trailText
                )
            }
        }

        if (trailItem != null) {
            trailItem()
        }
    }
}