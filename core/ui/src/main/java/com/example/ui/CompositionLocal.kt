package com.example.ui

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.ui.snackBar.SnackbarViewModel

val LocalSnackbarViewModel = staticCompositionLocalOf<SnackbarViewModel> {
    error("SnackbarViewModel not provided")
}