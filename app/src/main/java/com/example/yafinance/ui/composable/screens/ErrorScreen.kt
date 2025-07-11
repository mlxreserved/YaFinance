package com.example.yafinance.ui.composable.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.yafinance.R
//import com.example.yafinance.ui.LocalTopAppBarViewModel

@Composable
fun ErrorScreen(
    screenTitleId: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadId: Int? = null,
    onLeadIconClick: (() -> Unit)? = null,
) {
//    val topAppBarViewModel = LocalTopAppBarViewModel.current

//    LaunchedEffect(Unit) {
//        topAppBarViewModel.update(
//            TopAppBarState(
//                titleId = screenTitleId,
//                leadId = leadId,
//                onLeadIconClick = onLeadIconClick
//            )
//        )
//    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = YaFinanceTheme.colors.primaryText,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextButton(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = YaFinanceTheme.colors.primaryBackground,
                contentColor = YaFinanceTheme.colors.primaryText
            )
        ) {
            Text(
                text = stringResource(R.string.error_loading_button),
                color = YaFinanceTheme.colors.white
            )
        }
    }
}