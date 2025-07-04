package com.example.yafinance.ui.screens.accounts.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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