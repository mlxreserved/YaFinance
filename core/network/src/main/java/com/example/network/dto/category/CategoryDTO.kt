package com.example.network.dto.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)