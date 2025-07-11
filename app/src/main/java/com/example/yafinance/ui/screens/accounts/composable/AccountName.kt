package com.example.yafinance.ui.screens.accounts.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem

@Composable
fun AccountName(
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