package com.example.network.dto.account.history

import kotlinx.serialization.Serializable

@Serializable
data class AccountStateDTO(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String
)