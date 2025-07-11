package com.example.ui.components.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ui.R

@Composable
fun CustomSnackbar(message: String?, onDismiss: () -> Unit) {
    Snackbar(
        modifier = Modifier.padding(4.dp),
        dismissAction = {
            IconButton(onClick = { onDismiss() }) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
    ) {
        Text(text = message ?: stringResource(R.string.error_template))
    }
}