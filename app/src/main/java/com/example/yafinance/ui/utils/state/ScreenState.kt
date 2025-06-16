package com.example.yafinance.ui.utils.state

sealed interface ScreenState<out T> {
    data class Success <T> (val result: List<T>) : ScreenState<T>
    data class Error(val message: String) : ScreenState<Nothing>
    object Loading : ScreenState<Nothing>
    object Empty : ScreenState<Nothing>
}