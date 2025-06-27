package com.example.yafinance.ui.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton


/** ViewModel предоставляющая возможность показывать snackbar
 *  и хранящая его состояние **/
@Singleton
class SnackbarViewModel @Inject constructor() : ViewModel() {
    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage

    fun showMessage(message: String) {
        _snackbarMessage.update { message }
    }

    fun clearMessage() {
        _snackbarMessage.update { null }
    }
}