package com.example.yafinance.ui.screens.editAccount.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import com.example.yafinance.ui.LocalTopAppBarViewModel

@Composable
fun EditAccountContent(
    accountName: String,
    currentSum: String,
    currency: String,
    onAccountNameChange: (String) -> Unit,
    onChangeCurrencyClick: () -> Unit,
    onBalanceValueChange: (String) -> Unit,
    onTrailIconClick: () -> Unit,
    onLeadIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
//    val topAppBarViewModel = LocalTopAppBarViewModel.current

//    LaunchedEffect(Unit) {
//        topAppBarViewModel.update(
//            TopAppBarState(
//                titleId = R.string.my_account,
//                trailId = R.drawable.ic_save,
//                leadId = R.drawable.ic_cross,
//                onTrailIconClick = onTrailIconClick,
//                onLeadIconClick = onLeadIconClick
//            )
//        )
//    }

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