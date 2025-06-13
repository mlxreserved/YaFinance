package com.example.yafinance.ui.screens.account.composable

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
import com.example.yafinance.ui.composable.listItem.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun Currency(currency: String, trailIcon: ImageVector) {
    CustomListItem(
        title = { Text(stringResource(R.string.currency)) },
        trailText = currency,
        trailItem = {
            Icon(
                imageVector = trailIcon,
                contentDescription = null,
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        backgroundContainerColor = YaFinanceTheme.colors.secondaryBackground,
        hasDivider = false,
        modifier = Modifier.height(56.dp)

    )
}