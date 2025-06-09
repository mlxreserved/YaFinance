package com.example.yafinance.ui.utils.state

sealed interface ScreenState {
    data class Success <T> (val result: List<T>) : ScreenState
    data class Error(val message: String) : ScreenState
    object Loading : ScreenState
    object Empty : ScreenState
}