package com.example.domain.usecase.category.inter

import com.example.domain.model.category.Category
import com.example.model.result.Result

interface GetCategoriesByTypeUseCase {
    suspend fun getCategoriesByType(isIncome: Boolean): Result<List<Category>>
}