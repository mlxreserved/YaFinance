package com.example.yafinance.domain.models.income

data class Income(
    val id: Int,
    val leadIcon: String,
    val title: String, // Название - берем из Transaction
    val subtitle: String? = null, // Комментарий - берем из Transaction
    val amount: String, // Сумма расходов - берем из Transaction
    val currency: String,
    val transactionDate: String
)