package com.example.network.dto.transaction.response

import com.example.network.dto.category.CategoryDTO
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponseDTO(
    val id: Int,
    val account: AccountBriefDTO,
    val category: CategoryDTO,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String
)
