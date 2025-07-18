package com.example.ui.components.listItems

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.R
import com.example.ui.components.listItems.customListItem.CustomListItem

@Composable
fun TotalItem(
    trailText: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
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
        backgroundContainerColor = backgroundColor,
        hasDivider = hasDivider,
        modifier = modifier
    )
}