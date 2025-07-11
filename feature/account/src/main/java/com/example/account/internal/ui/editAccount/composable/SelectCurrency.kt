package com.example.account.internal.ui.editAccount.composable

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.account.R
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.components.listItems.customListItem.CustomListItem

@Composable
internal fun SelectCurrency(
    currency: String,
    onChangeCurrencyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomListItem(
        title = {
            Text(
                text = stringResource(R.string.currency),
                style = YaFinanceTheme.typography.title
            )
        },
        trailText = currency,
        backgroundContainerColor = YaFinanceTheme.colors.white,
        trailItem = {
            Icon(
                ImageVector.vectorResource(R.drawable.ic_more_vert),
                contentDescription = null
            )
        },
        modifier = modifier
            .clickable { onChangeCurrencyClick() }
    )
}