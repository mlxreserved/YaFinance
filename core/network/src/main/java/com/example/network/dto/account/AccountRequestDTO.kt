package com.example.network.dto.account

import kotlinx.serialization.Serializable

@Serializable
data class AccountRequestDTO(
    val name: String,
    val balance: String,
    val currency: String
)
