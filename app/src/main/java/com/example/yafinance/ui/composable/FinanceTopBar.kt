package com.example.yafinance.ui.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceTopBar(str: String) {

    TopAppBar(
        title = {
            Text(str)
        },
    )
}