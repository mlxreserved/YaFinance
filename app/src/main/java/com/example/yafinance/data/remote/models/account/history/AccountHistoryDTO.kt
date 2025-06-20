package com.example.yafinance.data.remote.models.account.history

import kotlinx.serialization.Serializable

@Serializable
data class AccountHistoryDTO(
    val id: Int,
    val accountId: Int,
    val changeType: String,
    val previousState: AccountStateDTO?,
    val newState: AccountStateDTO,
    val changeTimestamp: String,
    val createdAt: String
)