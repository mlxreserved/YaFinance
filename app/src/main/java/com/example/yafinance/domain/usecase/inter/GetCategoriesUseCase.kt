package com.example.yafinance.domain.usecase.inter

import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.domain.utils.Result

/** UseCase для получения категорий **/
interface GetCategoriesUseCase {
    suspend fun getCategories(): Result<List<Category>>
}