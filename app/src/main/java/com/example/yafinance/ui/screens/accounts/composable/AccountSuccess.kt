package com.example.yafinance.ui.screens.accounts.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.utils.formatWithSpaces
import com.example.yafinance.ui.utils.state.TopAppBarState

@Composable
fun AccountSuccess(
    account: Account,
    onTrailIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topAppBarViewModel = LocalTopAppBarViewModel.current

    LaunchedEffect(Unit) {
        topAppBarViewModel.update(
            TopAppBarState(
                titleId = R.string.my_account,
                trailId = R.drawable.ic_edit,
                onTrailIconClick = onTrailIconClick
            )
        )
    }

    val balanceFormattedAmount = account.sum.formatWithSpaces()
    val trailBalanceText = "$balanceFormattedAmount ${account.currency}"

    Column(modifier = modifier) {
        Balance(
            trailText = trailBalanceText
        )

        AccountName(
            accountName = account.name
        )

        Currency(
            currency = account.currency
        )
    }
}