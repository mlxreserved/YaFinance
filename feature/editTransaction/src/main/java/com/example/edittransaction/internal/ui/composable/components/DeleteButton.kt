package com.example.edittransaction.internal.ui.composable.components

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme

@Composable
fun DeleteButton(
    onClick: () -> Unit,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = YaFinanceTheme.colors.errorColor
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(text),
            color = YaFinanceTheme.colors.white,
            style = YaFinanceTheme.typography.title
        )
    }
}