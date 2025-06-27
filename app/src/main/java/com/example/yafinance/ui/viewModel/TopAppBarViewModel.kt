package com.example.yafinance.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.yafinance.ui.utils.state.TopAppBarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton


/**
 * ViewModel для предоставления состояния top app bar в экраны и для изменения состояния
 * **/
@Singleton
class TopAppBarViewModel @Inject constructor() : ViewModel() {
    private val _topAppBarState = MutableStateFlow(TopAppBarState())
    val topAppBarState: StateFlow<TopAppBarState> = _topAppBarState.asStateFlow()

    fun update(topAppBarState: TopAppBarState) {
        _topAppBarState.update {
            topAppBarState
        }
    }
}