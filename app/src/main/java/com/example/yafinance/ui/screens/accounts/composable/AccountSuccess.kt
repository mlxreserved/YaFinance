package com.example.yafinance.ui.screens.accounts.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.utils.formatWithSpaces
import com.example.yafinance.ui.utils.state.TopAppBarState

@Composable
fun AccountSuccess(
    account: Account,
    onTrailIconClick: () -> Unit,
    onBalanceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topAppBarViewModel = LocalTopAppBarViewModel.current

    topAppBarViewModel.update(
        TopAppBarState(
            titleId = R.string.my_account,
            trailId = R.drawable.ic_edit,
            onTrailIconClick = onTrailIconClick
        )
    )

    val balanceFormattedAmount = account.sum.formatWithSpaces()
    val trailBalanceText = "$balanceFormattedAmount ${account.currency}"
    val trailIcon = ImageVector.vectorResource(R.drawable.ic_more_vert)

    Column(modifier = modifier) {
        Balance(
            trailText = trailBalanceText,
            trailIcon = trailIcon,
            onBalanceClick = onBalanceClick
        )

        Currency(currency = account.currency, trailIcon = trailIcon)
    }
}