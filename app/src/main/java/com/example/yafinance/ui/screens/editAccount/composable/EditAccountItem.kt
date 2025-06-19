package com.example.yafinance.ui.screens.editAccount.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.ui.composable.listItems.customListItem.Lead
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun EditAccountItem(
    currentSum: String,
    currency: String,
    onTextChange: (String) -> Unit
) {
    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(YaFinanceTheme.colors.white)
                .height(56.dp)
        ) {

            Lead(
                leadIcon = stringResource(R.string.balance_lead_icon),
                backgroundColor = YaFinanceTheme.colors.white,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )

            Text(
                text = stringResource(R.string.balance),
                style = YaFinanceTheme.typography.title,
                modifier = Modifier.weight(0.35f)
            )

            TextField(
                value = currentSum,
                onValueChange = onTextChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = YaFinanceTheme.typography.title.copy(textAlign = TextAlign.End),
                visualTransformation = VisualTransformation.None,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YaFinanceTheme.colors.white,
                    unfocusedContainerColor = YaFinanceTheme.colors.white,
                    disabledContainerColor = YaFinanceTheme.colors.white,
                    focusedIndicatorColor = YaFinanceTheme.colors.white,
                    unfocusedIndicatorColor = YaFinanceTheme.colors.white,
                    disabledIndicatorColor = YaFinanceTheme.colors.white,
                ),
                trailingIcon = {
                    Text(text = currency, style = YaFinanceTheme.typography.title)
                },
                modifier = Modifier
                    .weight(0.45f)
            )

            IconButton(
                onClick = {},
                modifier = Modifier
                    .clip(shape = RectangleShape)
                    .fillMaxHeight()
                    .width(48.dp)
                    .background(YaFinanceTheme.colors.deleteColor)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
                    contentDescription = "Очистить",
                    tint = YaFinanceTheme.colors.white,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}