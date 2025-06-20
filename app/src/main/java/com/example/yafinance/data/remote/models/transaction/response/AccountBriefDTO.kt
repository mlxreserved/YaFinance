package com.example.yafinance.data.remote.models.transaction.response

import kotlinx.serialization.Serializable

@Serializable
data class AccountBriefDTO(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String
)
