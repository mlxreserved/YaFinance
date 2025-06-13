package com.example.yafinance.ui.composable.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.yafinance.R
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun ErrorScreen(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = YaFinanceTheme.colors.primaryText
        )
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = YaFinanceTheme.colors.primaryBackground,
                contentColor = YaFinanceTheme.colors.primaryText
            )
        ) {
            Text(text = stringResource(R.string.error_loading_button))
        }
    }
}