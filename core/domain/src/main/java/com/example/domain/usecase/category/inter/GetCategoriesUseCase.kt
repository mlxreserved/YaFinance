package com.example.domain.usecase.category.inter

import com.example.domain.model.category.Category
import com.example.model.result.Result

/** UseCase для получения категорий **/
interface GetCategoriesUseCase {
    suspend fun getCategories(): Result<List<Category>>
}