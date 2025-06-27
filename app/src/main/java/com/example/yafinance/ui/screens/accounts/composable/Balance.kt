package com.example.yafinance.ui.screens.accounts.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItems.customListItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun Balance(trailText: String, trailIcon: ImageVector, onBalanceClick: () -> Unit) {
    CustomListItem(
        title = { Text(stringResource(R.string.balance)) },
        leadIcon = stringResource(R.string.balance_lead_icon),
        trailText = trailText,
        trailItem = {
            Icon(
                imageVector = trailIcon,
                contentDescription = null,
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground,
        backgroundLeadColor = YaFinanceTheme.colors.surface,
        modifier = Modifier
            .height(56.dp)
            .clickable(onClick = { onBalanceClick() })
    )
}