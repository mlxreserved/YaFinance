package com.example.yafinance.data.remote.models.account

import kotlinx.serialization.Serializable

@Serializable
data class AccountDTO(
    val id: Int,
    val userId: Int,
    val name: String,
    val balance: String,
    val currency: String,
    val createdAt: String,
    val updatedAt: String
)
