package com.example.domain.model.expense

data class ExpenseDetailed(
    val id: Int,
    val accountId: Int,
    val accountName: String,
    val categoryId: Int,
    val categoryName: String,
    val sum: String,
    val transactionDate: String,
    val comment: String? = null,
    val currency: String
)
