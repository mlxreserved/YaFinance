package com.example.yafinance.ui.screens

import androidx.lifecycle.ViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel <T> : ViewModel() {

    protected open val _screenState: MutableStateFlow<ScreenState<T>> =
        MutableStateFlow(ScreenState.Loading)
    open val screenState: StateFlow<ScreenState<T>> = _screenState.asStateFlow()

    protected fun updateState(state: ScreenState<T>) {
        _screenState.update { state }
    }

    protected fun <T> BaseViewModel<List<T>>.updateStateBasedOnListContent(data: List<T>) {
        updateState(if (data.isEmpty()) ScreenState.Empty
        else ScreenState.Success(data))
    }

}

