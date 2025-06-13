package com.example.yafinance.data.models.transaction.response

import com.example.yafinance.data.models.category.Category

data class TransactionResponse(
    val id: Int,
    val account: AccountBrief,
    val category: Category,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String
)
