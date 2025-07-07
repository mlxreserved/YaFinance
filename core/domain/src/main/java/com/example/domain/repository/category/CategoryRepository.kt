package com.example.domain.repository.category

import com.example.domain.model.category.Category
import com.example.model.result.Result

interface CategoryRepository {
    suspend fun getCategories(): Result<List<Category>>
}