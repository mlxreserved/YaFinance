package com.example.yafinance.ui.screens.history.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.toEndDateString
import com.example.yafinance.ui.utils.toStartDateString
import java.util.Date

@Composable
fun DateItem(isStart: Boolean, date: Date) {
    CustomListItem(
        title = {
            if(isStart) {
                Text(
                    stringResource(R.string.start_date),
                    style = YaFinanceTheme.typography.title
                )
            } else {
                Text(
                    stringResource(R.string.end_date),
                    style = YaFinanceTheme.typography.title
                )
            }
        },
        trailText = if(isStart) date.toStartDateString() else date.toEndDateString(),
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground
    )
}