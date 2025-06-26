package com.example.yafinance.ui.utils.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class TopAppBarState(
    val titleId: Int? = null,
    val trailId: Int? = null,
    val leadId: Int? = null,
    val onTrailIconClick: (() -> Unit)? = null,
    val onLeadIconClick: (() -> Unit)? = null
)

/**
 * Провайдер для предоставления состояния top app bar в экраны и для изменения состояния
 * **/

object TopAppBarStateProvider {
    var topAppBarState: TopAppBarState by mutableStateOf(TopAppBarState())
        private set

    fun update(topAppBarState: TopAppBarState) {
        this.topAppBarState = topAppBarState
    }
}