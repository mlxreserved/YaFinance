package com.example.network.dto.account.history

import kotlinx.serialization.Serializable

@Serializable
data class CertainAccountHistoryDTO(
    val accountId: Int,
    val accountName: String,
    val currency: String,
    val currentBalance: String,
    val history: AccountHistoryDTO
)