package com.example.yafinance.ui.composable.listItems

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.design.theme.customTheme.YaFinanceTheme

@Composable
fun TotalItem(
    trailText: String,
    modifier: Modifier = Modifier,
    hasDivider: Boolean = true
) {
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
        modifier = modifier
    )
}