package com.example.yafinance.ui.utils.state

import com.example.yafinance.domain.utils.ErrorModel

sealed interface ScreenState<out T> {
    data class Success <T> (val result: T) : ScreenState<T>
    data class Error(val message: ErrorModel, val count: Int = 0) : ScreenState<Nothing>
    object Loading : ScreenState<Nothing>
    object Empty : ScreenState<Nothing>
}
