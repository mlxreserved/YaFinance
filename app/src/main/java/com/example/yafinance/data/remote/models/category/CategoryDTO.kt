package com.example.yafinance.data.remote.models.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)