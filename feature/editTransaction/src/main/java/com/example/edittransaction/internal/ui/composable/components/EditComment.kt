package com.example.edittransaction.internal.ui.composable.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.edittransaction.R

@Composable
fun EditComment(
    comment: String,
    onEditComment: (String) -> Unit
) {
    Column {
        TextField(
            value = comment,
            placeholder = { Text(text = stringResource(R.string.comment)) },
            onValueChange = onEditComment,
            singleLine = true,
            textStyle = YaFinanceTheme.typography.title.copy(textAlign = TextAlign.Start),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = YaFinanceTheme.colors.surface,
                unfocusedContainerColor = YaFinanceTheme.colors.surface,
                disabledContainerColor = YaFinanceTheme.colors.surface,
                focusedIndicatorColor = YaFinanceTheme.colors.surface,
                unfocusedIndicatorColor = YaFinanceTheme.colors.surface,
                disabledIndicatorColor = YaFinanceTheme.colors.surface,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )

        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}