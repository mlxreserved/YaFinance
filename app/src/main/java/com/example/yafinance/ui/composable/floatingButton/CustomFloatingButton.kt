package com.example.yafinance.ui.composable.floatingButton

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun CustomFloatingButton(onClick: () -> Unit) {
    FloatingActionButton(
        containerColor = YaFinanceTheme.colors.primaryBackground,
        contentColor = YaFinanceTheme.colors.surface,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
        shape = CircleShape,
        onClick = { onClick() }
    ) {
        Icon(Icons.Filled.Add, contentDescription = null)
    }
}