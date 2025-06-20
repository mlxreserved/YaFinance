package com.example.yafinance.data.remote.models.transaction.request

import kotlinx.serialization.Serializable

@Serializable
data class TransactionRequestDTO(
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String?
)
