package com.example.domain.model.income

data class IncomeUpdate (
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String? = null
)