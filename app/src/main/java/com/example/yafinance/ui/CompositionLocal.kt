package com.example.yafinance.ui

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.yafinance.ui.viewModel.SnackbarViewModel
import com.example.yafinance.ui.viewModel.TopAppBarViewModel

val LocalTopAppBarViewModel = staticCompositionLocalOf<TopAppBarViewModel> {
    error("TopAppBarViewModel not provided")
}

val LocalSnackbarViewModel = staticCompositionLocalOf<SnackbarViewModel> {
    error("SnackbarViewModel not provided")
}