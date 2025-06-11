package com.example.yafinance.ui.screens.account.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.utils.Format

@Composable
fun AccountSuccess(account: Account, modifier: Modifier = Modifier) {
    val balanceFormattedAmount = Format.formatWithSpaces(account.sum)
    val trailBalanceText = "$balanceFormattedAmount ${account.currency}"
    val trailIcon = ImageVector.vectorResource(R.drawable.more_vert)
    Column(modifier = modifier) {
        Balance(trailText = trailBalanceText, trailIcon = trailIcon)

        Currency(currency = account.currency, trailIcon = trailIcon)
    }
}