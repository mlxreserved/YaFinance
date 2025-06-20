package com.example.yafinance.ui.composable.listItems

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun TotalItem(trailText: String, hasDivider: Boolean = true) {
    CustomListItem(
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = stringResource(R.string.total)
            )
        },
        trailText = trailText,
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground,
        hasDivider = hasDivider,
        modifier = Modifier.height(56.dp)
    )
}