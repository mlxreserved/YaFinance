package com.example.yafinance.ui.screens.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.composable.CustomFloatingButton
import com.example.yafinance.ui.screens.account.composable.Balance
import com.example.yafinance.ui.screens.account.composable.Currency
import com.example.yafinance.ui.utils.Format

@Composable
fun AccountsScreen(account: Account) {
    val balanceFormattedAmount = Format.formatWithSpaces(account.sum)
    val trailBalanceText = "$balanceFormattedAmount ${account.currency}"
    val trailIcon = ImageVector.vectorResource(R.drawable.more_vert)
    Column {
        Balance(trailText = trailBalanceText, trailIcon = trailIcon)

        Currency(currency = account.currency, trailIcon = trailIcon)
    }
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize().padding(end = 16.dp, bottom = 14.dp)
    ){
        CustomFloatingButton()
    }
}