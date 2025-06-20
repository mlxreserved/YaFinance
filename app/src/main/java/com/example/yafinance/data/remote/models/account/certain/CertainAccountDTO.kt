package com.example.yafinance.data.remote.models.account.certain

import kotlinx.serialization.Serializable

@Serializable
data class CertainAccountDTO(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String,
    val incomeStats: List<StatItemDTO>,
    val expenseStats: List<StatItemDTO>,
    val createdAt: String,
    val updatedAt: String
)
