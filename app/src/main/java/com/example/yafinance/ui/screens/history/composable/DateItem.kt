package com.example.yafinance.ui.screens.history.composable

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.utils.toDateString
import java.util.Date

@Composable
fun DateItem(
    isStart: Boolean,
    date: Date,
    onDateItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomListItem(
        title = {
            if (isStart) {
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
        trailText = date.toDateString(),
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground,
        modifier = modifier
            .clickable { onDateItemClick() }
    )
}