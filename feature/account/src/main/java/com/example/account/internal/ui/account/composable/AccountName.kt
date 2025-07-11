package com.example.account.internal.ui.account.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.account.R
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.components.listItems.customListItem.CustomListItem

@Composable
internal fun AccountName(
    accountName: String,
    modifier: Modifier = Modifier
) {
    CustomListItem(
        title = {
            Text(
                text = stringResource(R.string.account_name),
                style = YaFinanceTheme.typography.title
            )
        },
        trailText = accountName,
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground,
        modifier = modifier
    )
}