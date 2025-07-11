package com.example.account.internal.ui.editAccount.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun EditAccountContent(
    accountName: String,
    currentSum: String,
    currency: String,
    onAccountNameChange: (String) -> Unit,
    onChangeCurrencyClick: () -> Unit,
    onBalanceValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        EditBalance(
            currency = currency,
            currentSum = currentSum,
            onBalanceValueChange = onBalanceValueChange
        )

        EditAccountName(
            onAccountNameChange = onAccountNameChange,
            accountName = accountName
        )

        SelectCurrency(
            currency = currency,
            onChangeCurrencyClick = onChangeCurrencyClick,
            modifier = Modifier
                .height(56.dp)
        )
    }
}