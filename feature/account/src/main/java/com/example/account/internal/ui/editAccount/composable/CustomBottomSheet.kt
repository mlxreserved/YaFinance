package com.example.account.internal.ui.editAccount.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.account.R
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.components.listItems.customListItem.CustomListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CustomBottomSheet(
    onDismiss: () -> Unit,
    onSelectCurrency: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        containerColor = YaFinanceTheme.colors.white,
        onDismissRequest = onDismiss,
        modifier = modifier
    ) {
        CurrencyItem(
            leadIconId = R.drawable.ruble,
            currencyId = R.string.ruble,
            currencyIconId = R.string.ruble_icon,
            onSelectCurrency = onSelectCurrency,
            onDismiss = onDismiss
        )
        CurrencyItem(
            leadIconId = R.drawable.dollar,
            currencyId = R.string.dollar,
            currencyIconId = R.string.dollar_icon,
            onSelectCurrency = onSelectCurrency,
            onDismiss = onDismiss

        )
        CurrencyItem(
            leadIconId = R.drawable.euro,
            currencyId = R.string.euro,
            currencyIconId = R.string.euro_icon,
            onSelectCurrency = onSelectCurrency,
            onDismiss = onDismiss
        )
        CancelItem(
            modifier = Modifier
                .height(72.dp)
                .clickable { onDismiss() }
        )
    }
}

@Composable
fun CurrencyItem(
    leadIconId: Int,
    currencyId: Int,
    currencyIconId: Int,
    onDismiss: () -> Unit,
    onSelectCurrency: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currencyIcon = stringResource(currencyIconId)
    val currencyTitle = stringResource(currencyId)
    val leadIcon = ImageVector.vectorResource(leadIconId)

    CustomListItem(
        leadIcon = {
            Icon(
                leadIcon,
                contentDescription = null
            )
        },
        backgroundLeadColor = YaFinanceTheme.colors.white,
        backgroundContainerColor = YaFinanceTheme.colors.white,
        title = {
            Text(
                text = "$currencyTitle $currencyIcon"
            )
        },
        modifier = modifier
            .height(72.dp)
            .clickable {
                onSelectCurrency(currencyIcon)
                onDismiss()
            }
    )
}

@Composable
fun CancelItem(
    modifier: Modifier = Modifier
) {
    CustomListItem(
        leadIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.cancel_element),
                tint = YaFinanceTheme.colors.white,
                contentDescription = null
            )
        },
        backgroundLeadColor = YaFinanceTheme.colors.errorColor,
        backgroundContainerColor = YaFinanceTheme.colors.errorColor,
        title = {
            Text(
                text = stringResource(R.string.cancel),
                color = YaFinanceTheme.colors.white
            )
        },
        modifier = modifier
    )
}