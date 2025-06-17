package com.example.yafinance.ui.screens.history.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun SumItem(sum: String) {
    CustomListItem(
        title = { Text(stringResource(R.string.sum)) },
        trailText = sum,
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground,
        hasDivider = false
    )
}