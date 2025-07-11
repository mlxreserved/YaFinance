package com.example.edittransaction.internal.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.edittransaction.R
import com.example.ui.components.listItems.customListItem.Lead
import com.example.utils.extensions.string.isEmoji

@Composable
internal fun EditBalance(
    currentSum: String,
    currency: String,
    onBalanceValueChange: (String) -> Unit,
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
                text = stringResource(R.string.amount),
                style = YaFinanceTheme.typography.title,
                modifier = Modifier.weight(0.35f)
            )

            TextField(
                value = currentSum,
                onValueChange = onBalanceValueChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = YaFinanceTheme.typography.title.copy(textAlign = TextAlign.End),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YaFinanceTheme.colors.white,
                    unfocusedContainerColor = YaFinanceTheme.colors.white,
                    disabledContainerColor = YaFinanceTheme.colors.white,
                    focusedIndicatorColor = YaFinanceTheme.colors.white,
                    unfocusedIndicatorColor = YaFinanceTheme.colors.white,
                    disabledIndicatorColor = YaFinanceTheme.colors.white,
                ),
                suffix = {
                    Text(text = " $currency", style = YaFinanceTheme.typography.title)
                },
                modifier = Modifier
                    .weight(0.45f)
            )

        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}