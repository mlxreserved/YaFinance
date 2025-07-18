package com.example.ui.dateItem

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.R
import com.example.ui.components.listItems.customListItem.CustomListItem
import com.example.utils.extensions.string.toDateString
import java.util.Date

@Composable
fun DateItem(
    date: Date,
    onDateItemClick: () -> Unit,
    modifier: Modifier = Modifier,
    isStart: Boolean? = null
) {
    CustomListItem(
        title = {
            if (isStart == true) {
                Text(
                    stringResource(R.string.start_date),
                    style = YaFinanceTheme.typography.title
                )
            } else if (isStart == false) {
                Text(
                    stringResource(R.string.end_date),
                    style = YaFinanceTheme.typography.title
                )
            } else {
                Text(
                    stringResource(R.string.date),
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