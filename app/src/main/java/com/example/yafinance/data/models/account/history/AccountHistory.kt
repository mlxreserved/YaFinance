package com.example.yafinance.data.models.account.history

data class AccountHistory(
    val id: Int,
    val accountId: Int,
    val changeType: String,
    val previousState: AccountState?,
    val newState: AccountState,
    val changeTimestamp: String,
    val createdAt: String
)