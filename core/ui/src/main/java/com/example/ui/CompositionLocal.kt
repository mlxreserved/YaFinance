package com.example.ui

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.ui.snackbar.SnackbarViewModel

val LocalSnackbarViewModel = staticCompositionLocalOf<SnackbarViewModel> {
    error("SnackbarViewModel not provided")
}