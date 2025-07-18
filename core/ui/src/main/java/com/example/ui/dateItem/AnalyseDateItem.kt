package com.example.ui.dateItem

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.R
import com.example.ui.chip.CustomChip
import com.example.ui.components.listItems.customListItem.CustomListItem
import java.util.Date

@Composable
fun AnalyseDateItem(
    date: Date,
    onDateItemClick: () -> Unit,
    modifier: Modifier = Modifier,
    isStart: Boolean? = null
) {
    CustomListItem(
        title = {
            if (isStart == true) {
                Text(
                    stringResource(R.string.period_start),
                    style = YaFinanceTheme.typography.title
                )
            } else{
                Text(
                    stringResource(R.string.period_end),
                    style = YaFinanceTheme.typography.title
                )
            }
        },
        trailItem = {
            CustomChip(
                date = date,
                onChipClick = onDateItemClick
            )
        },
        backgroundContainerColor = YaFinanceTheme.colors.surface,
        modifier = modifier
    )
}