package com.example.ui.components.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme

// TODO: Убрать TopAppBarState

@Composable
fun EmptyScreen(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    addText: String? = null
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = YaFinanceTheme.typography.title,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (onClick != null && addText != null) {
            EmptyScreenAddButton(onClick = onClick, addText = addText)
        }
    }
}