package com.example.domain.model.expense

data class ExpenseUpdate (
    val serverId: Int? = null,
    val localId: Int? = null,
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String? = null
)