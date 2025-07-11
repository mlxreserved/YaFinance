package com.example.edittransaction.internal.ui.composable

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.edittransaction.R

@Composable
fun EditComment(
    comment: String,
    onEditComment: (String) -> Unit
) {
    TextField(
        value = comment,
        placeholder = { Text(text = stringResource(R.string.comment)) },
        onValueChange = onEditComment,
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
    )
}