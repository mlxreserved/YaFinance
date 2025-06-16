package com.example.yafinance.data.remote.models.account.history

import kotlinx.serialization.Serializable

@Serializable
data class AccountStateDTO(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String
)