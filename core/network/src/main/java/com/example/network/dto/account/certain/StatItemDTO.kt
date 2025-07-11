package com.example.network.dto.account.certain

import kotlinx.serialization.Serializable

@Serializable
data class StatItemDTO(
    val categoryId: Int,
    val categoryName: String,
    val emoji: String,
    val amount: String
)
