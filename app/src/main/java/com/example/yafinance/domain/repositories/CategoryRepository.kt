package com.example.yafinance.domain.repositories

import com.example.yafinance.domain.models.category.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}