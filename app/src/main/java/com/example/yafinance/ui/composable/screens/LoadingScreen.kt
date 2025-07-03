package com.example.yafinance.ui.composable.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.state.TopAppBarState

@Composable
fun LoadingScreen(screenTitleId: Int) {
    val topAppBarViewModel = LocalTopAppBarViewModel.current

    LaunchedEffect(Unit) {
        topAppBarViewModel.update(topAppBarState = TopAppBarState(titleId = screenTitleId))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = YaFinanceTheme.colors.primaryBackground
        )
    }
}