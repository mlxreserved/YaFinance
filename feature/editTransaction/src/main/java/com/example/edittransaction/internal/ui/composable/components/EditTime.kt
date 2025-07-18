package com.example.edittransaction.internal.ui.composable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.edittransaction.R
import com.example.ui.components.listItems.customListItem.CustomListItem
import com.example.utils.extensions.string.toTimeString
import java.util.Date

@Composable
fun EditTime(startTime: Date, onTimeClick: () -> Unit) {
    CustomListItem(
        title = {
            Text(
                text = stringResource(R.string.time),
                style = YaFinanceTheme.typography.title
            )
        },
        trailText = startTime.toTimeString(),
        modifier = Modifier
            .height(70.dp)
            .clickable {
                onTimeClick()
            }
    )
}