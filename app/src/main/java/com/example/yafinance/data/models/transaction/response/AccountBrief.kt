package com.example.yafinance.data.models.transaction.response

data class AccountBrief(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String
)
