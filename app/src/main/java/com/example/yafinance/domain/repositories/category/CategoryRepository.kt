package com.example.yafinance.domain.repositories.category

import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.domain.utils.Result

interface CategoryRepository {
    suspend fun getCategories(): Result<List<Category>>
}