package com.example.yafinance.ui.screens.accounts.composable.success

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.utils.Format
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun AccountSuccess(
    accounts: List<Account>,
    onTrailIconClick: () -> Unit,
    onBalanceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.my_account,
            trailId = R.drawable.ic_edit,
            onTrailIconClick = onTrailIconClick
        )
    )

    LazyColumn(modifier = modifier) {
        items(items = accounts, key = { it.id }) { account ->
            val balanceFormattedAmount = Format.formatWithSpaces(account.sum)
            val trailBalanceText = "$balanceFormattedAmount ${account.currency}"
            val trailIcon = ImageVector.vectorResource(R.drawable.more_vert)

            Column(modifier = modifier) {
                Balance(trailText = trailBalanceText, trailIcon = trailIcon, onBalanceClick = onBalanceClick)

                Currency(currency = account.currency, trailIcon = trailIcon)
            }
        }
    }
}