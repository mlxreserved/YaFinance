package com.example.ui.components.floatingButton

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme

@Composable
fun CustomFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        containerColor = YaFinanceTheme.colors.primaryBackground,
        contentColor = YaFinanceTheme.colors.surface,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
        shape = CircleShape,
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, contentDescription = null)
    }
}