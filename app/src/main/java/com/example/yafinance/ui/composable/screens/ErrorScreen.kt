package com.example.yafinance.ui.composable.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun ErrorScreen(screenTitleId: Int, text: String, onClick: () -> Unit) {

    TopAppBarStateProvider.update(TopAppBarState(titleId = screenTitleId))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = YaFinanceTheme.colors.primaryText,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextButton(
            onClick = { onClick() },
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