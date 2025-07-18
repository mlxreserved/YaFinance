package com.example.ui.components.screens

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
import com.example.ui.R

// TODO: Убрать TopAppBarState


@Composable
fun ErrorScreen(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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