package com.example.yafinance.data.remote.models.account.certain

import kotlinx.serialization.Serializable

@Serializable
data class StatItemDTO(
    val categoryId: Int,
    val categoryName: String,
    val emoji: String,
    val amount: String
)
