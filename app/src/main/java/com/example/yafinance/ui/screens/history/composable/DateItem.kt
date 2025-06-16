package com.example.yafinance.ui.screens.history.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun DateItem(date: String) {
    CustomListItem(
        title = {
            Text(
                stringResource(R.string.start_date),
                style = YaFinanceTheme.typography.title
            )
        },
        trailText = date
    )
}