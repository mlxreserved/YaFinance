package com.example.yafinance.data.remote.models.account

import kotlinx.serialization.Serializable

@Serializable
data class AccountRequestDTO(
    val name: String,
    val balance: String,
    val currency: String
)
