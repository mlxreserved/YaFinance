package com.example.domain.repository.category

import com.example.domain.model.category.Category
import com.example.model.result.Result

interface CategoryRepository {
    suspend fun getCategories(): Result<List<Category>>

    suspend fun getCategoriesByType(isIncome: Boolean): Result<List<Category>>
}