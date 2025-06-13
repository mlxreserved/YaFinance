package com.example.yafinance.ui.screens.categories.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun CategorySearch(trailIcon: ImageVector) {
    var searchState by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchState,
        onValueChange = { searchState = it },
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
        modifier = Modifier.height(56.dp).fillMaxWidth(),
    )
}