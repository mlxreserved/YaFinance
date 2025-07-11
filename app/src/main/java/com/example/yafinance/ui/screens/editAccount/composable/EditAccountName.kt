package com.example.yafinance.ui.screens.editAccount.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.yafinance.R

@Composable
fun EditAccountName(
    onAccountNameChange: (String) -> Unit,
    accountName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(YaFinanceTheme.colors.white)
                .height(56.dp)
        ) {
            Text(
                text = stringResource(R.string.account_name),
                style = YaFinanceTheme.typography.title,
                modifier = Modifier
                    .weight(0.35f)
                    .padding(start = 16.dp)
            )

            TextField(
                value = accountName,
                onValueChange = onAccountNameChange,
                singleLine = true,
                textStyle = YaFinanceTheme.typography.title.copy(textAlign = TextAlign.End),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YaFinanceTheme.colors.white,
                    unfocusedContainerColor = YaFinanceTheme.colors.white,
                    disabledContainerColor = YaFinanceTheme.colors.white,
                    focusedIndicatorColor = YaFinanceTheme.colors.white,
                    unfocusedIndicatorColor = YaFinanceTheme.colors.white,
                    disabledIndicatorColor = YaFinanceTheme.colors.white,
                ),
                modifier = Modifier
                    .weight(0.45f)
            )
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}