package com.example.ui.components.screens

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.design.theme.customTheme.YaFinanceTheme

@Composable
fun EmptyScreenAddButton(onClick: () -> Unit, addText: String) {
    TextButton(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = YaFinanceTheme.colors.primaryBackground)
    ) {
        Text(
            text = addText,
            style = YaFinanceTheme.typography.subtitle,
            color = YaFinanceTheme.colors.white
        )
    }
}