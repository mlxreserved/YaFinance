package com.example.yafinance.ui.screens.accounts.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun Currency(
    currency: String,
    modifier: Modifier = Modifier
) {
    CustomListItem(
        title = {
            Text(
                text = stringResource(R.string.currency),
                style = YaFinanceTheme.typography.title
            )
        },
        trailText = currency,
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground,
        hasDivider = false,
        modifier = modifier
    )
}