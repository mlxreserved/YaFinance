package com.example.model.categoryTotal

data class CategoryTotal(
    val categoryId: Int,
    val totalAmount: Double,
    val currency: String,
    val categoryName: String,
    val leadIcon: String,
    val percentage: String
)
