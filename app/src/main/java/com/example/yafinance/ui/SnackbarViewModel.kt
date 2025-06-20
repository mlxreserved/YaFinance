package com.example.yafinance.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Singleton

@Singleton
class SnackbarViewModel : ViewModel() {
    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage

    fun showMessage(message: String) {
        _snackbarMessage.update { message }
    }

    fun clearMessage() {
        _snackbarMessage.update { null }
    }
}