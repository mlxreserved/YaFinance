package com.example.yafinance.ui.composable.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
@Composable
fun EmptyScreen(text: String, onClick: (() -> Unit)? = null, addText: String? = null) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, style = YaFinanceTheme.typography.title, modifier = Modifier.padding(bottom = 8.dp))
        if(onClick != null && addText != null) {
            EmptyScreenAddButton(onClick = onClick, addText = addText)
        }
    }
}