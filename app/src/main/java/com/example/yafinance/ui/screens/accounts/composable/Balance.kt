package com.example.yafinance.ui.screens.accounts.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.utils.isEmoji

@Composable
fun Balance(
    trailText: String,
    modifier: Modifier = Modifier
) {
    CustomListItem(
        title = {
            Text(
                text = stringResource(R.string.balance),
                style = YaFinanceTheme.typography.title
            )
        },
        leadIcon = {
            Text(
                text = stringResource(R.string.balance_lead_icon),
                style = if (stringResource(R.string.balance_lead_icon).isEmoji()) YaFinanceTheme.typography.emoji else YaFinanceTheme.typography.emojiText
            )
        },
        trailText = trailText,
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground,
        backgroundLeadColor = YaFinanceTheme.colors.surface,
        modifier = modifier
    )
}