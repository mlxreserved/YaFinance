package com.example.account.internal.ui.account.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.model.account.Account
import com.example.utils.extensions.string.formatWithSpaces

@Composable
internal fun AccountSuccess(
    account: Account,
    modifier: Modifier = Modifier
) {
    val balanceFormattedAmount = account.sum.formatWithSpaces()
    val trailBalanceText = "$balanceFormattedAmount ${account.currency}"

    Column(modifier = modifier) {
        Balance(
            trailText = trailBalanceText,
            modifier = Modifier
                .height(56.dp)
        )

        AccountName(
            accountName = account.name,
            modifier = Modifier
                .height(56.dp)
        )

        Currency(
            currency = account.currency,
            modifier = Modifier
                .height(56.dp)
        )
    }
}