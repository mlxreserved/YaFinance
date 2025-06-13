package com.example.yafinance.data.models.account.history

data class CertainAccountHistory(
    val accountId: Int,
    val accountName: String,
    val currency: String,
    val currentBalance: String,
    val history: AccountHistory
)