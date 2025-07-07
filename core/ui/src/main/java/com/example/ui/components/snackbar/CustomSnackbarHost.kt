package com.example.ui.components.snackbar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

@Composable
fun CustomSnackbarHost(hostState: SnackbarHostState, onDismiss: () -> Unit) {
    SnackbarHost(hostState = hostState) { data ->
        CustomSnackbar(
            message = data.visuals.message,
            onDismiss = onDismiss
        )
    }
}