package com.example.yafinance.data.remote.models.transaction

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDTO(
    val id: Int,
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String
)