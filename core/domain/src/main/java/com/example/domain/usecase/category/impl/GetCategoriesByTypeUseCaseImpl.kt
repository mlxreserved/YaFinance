package com.example.domain.usecase.category.impl

import com.example.domain.model.category.Category
import com.example.domain.repository.category.CategoryRepository
import com.example.domain.usecase.category.inter.GetCategoriesByTypeUseCase
import com.example.model.result.Result

class GetCategoriesByTypeUseCaseImpl(
    private val categoryRepository: CategoryRepository
) : GetCategoriesByTypeUseCase {
    override suspend fun getCategoriesByType(isIncome: Boolean): Result<List<Category>> =
        categoryRepository.getCategoriesByType(isIncome)
}