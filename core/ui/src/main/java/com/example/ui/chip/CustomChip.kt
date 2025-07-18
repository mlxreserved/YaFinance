package com.example.ui.chip

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.utils.extensions.string.toDateString
import java.util.Date

@Composable
fun CustomChip(
    date: Date,
    onChipClick: () -> Unit
) {
    SuggestionChip(
        onClick = { onChipClick() },
        label = { Text(
            text = date.toDateString(),
            style = YaFinanceTheme.typography.title,
            fontWeight = FontWeight.W500
        ) },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = YaFinanceTheme.colors.primaryBackground
        ),
        border = null,
        shape = CircleShape
    )
}