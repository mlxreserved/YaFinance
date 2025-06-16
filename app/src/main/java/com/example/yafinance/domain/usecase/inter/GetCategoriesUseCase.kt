package com.example.yafinance.domain.usecase.inter

import com.example.yafinance.domain.models.category.Category

interface GetCategoriesUseCase {
    suspend fun getCategories(): List<Category>
}