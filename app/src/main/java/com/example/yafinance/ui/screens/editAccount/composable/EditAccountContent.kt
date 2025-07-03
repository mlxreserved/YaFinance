package com.example.yafinance.ui.screens.editAccount.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun EditAccountContent(
    accountName: String,
    currentSum: String,
    currency: String,
    onAccountNameChange: (String) -> Unit,
    onChangeCurrencyClick: () -> Unit,
    onBalanceValueChange: (String) -> Unit
) {
    Column {
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
            onChangeCurrencyClick = onChangeCurrencyClick
        )
    }
}