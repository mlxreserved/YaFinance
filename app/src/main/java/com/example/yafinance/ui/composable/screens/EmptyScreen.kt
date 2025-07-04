package com.example.yafinance.ui.composable.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.state.TopAppBarState

@Composable
fun EmptyScreen(
    text: String,
    @StringRes screenTitleId: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    @DrawableRes leadId: Int? = null,
    onLeadIconClick: (() -> Unit)? = null,
    @DrawableRes trailId: Int? = null,
    onTrailIconClick: (() -> Unit)? = null,
    addText: String? = null
) {
    val topAppBarViewModel = LocalTopAppBarViewModel.current

    LaunchedEffect(Unit) {
        topAppBarViewModel.update(
            TopAppBarState(
                titleId = screenTitleId,
                leadId = leadId,
                onLeadIconClick = onLeadIconClick,
                trailId = trailId,
                onTrailIconClick = onTrailIconClick
            )
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = YaFinanceTheme.typography.title,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (onClick != null && addText != null) {
            EmptyScreenAddButton(onClick = onClick, addText = addText)
        }
    }
}