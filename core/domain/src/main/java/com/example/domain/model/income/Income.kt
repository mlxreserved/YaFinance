package com.example.domain.model.income

data class Income(
    val localId: Int,
    val serverId: Int,
    val accountId: Int,
    val categoryId: Int,
    val leadIcon: String,
    val title: String, // Название - берем из Transaction
    val subtitle: String? = null, // Комментарий - берем из Transaction
    val amount: String, // Сумма расходов - берем из Transaction
    val currency: String,
    val transactionDate: String
)