package com.example.yafinance.ui.utils.state

data class TopAppBarState(
    val titleId: Int? = null,
    val trailId: Int? = null,
    val leadId: Int? = null,
    val onTrailIconClick: (() -> Unit)? = null,
    val onLeadIconClick: (() -> Unit)? = null
)