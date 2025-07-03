package com.example.yafinance.ui.screens.accounts.composable

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun AccountName(accountName: String) {
    CustomListItem(
        title = {
            Text(
                text = stringResource(R.string.account_name),
                style = YaFinanceTheme.typography.title
            )
        },
        trailText = accountName,
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground,
        modifier = Modifier.height(56.dp)
    )
}