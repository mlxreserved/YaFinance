package com.example.yafinance.ui.screens.categories.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.yafinance.R
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun CategorySearch(
    onSearchChanged: (String) -> Unit,
    searchQuery: String,
    modifier: Modifier = Modifier
) {
    val trailIcon = ImageVector.vectorResource(R.drawable.ic_search)

    TextField(
        value = searchQuery,
        onValueChange = onSearchChanged,
        label = { Text(stringResource(R.string.find_category)) },
        textStyle = YaFinanceTheme.typography.title,
        trailingIcon = { Icon(trailIcon, contentDescription = null) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = YaFinanceTheme.colors.tertiaryBackground,
            focusedLabelColor = YaFinanceTheme.colors.tertiaryText,
            focusedTrailingIconColor = YaFinanceTheme.colors.tertiaryText,
            focusedIndicatorColor = YaFinanceTheme.colors.outlineColor,
            unfocusedContainerColor = YaFinanceTheme.colors.tertiaryBackground,
            unfocusedLabelColor = YaFinanceTheme.colors.tertiaryText,
            unfocusedTrailingIconColor = YaFinanceTheme.colors.tertiaryText,
            cursorColor = YaFinanceTheme.colors.tertiaryText,
            unfocusedIndicatorColor = YaFinanceTheme.colors.outlineColor
        ),
        singleLine = true,
        modifier = modifier
    )
}