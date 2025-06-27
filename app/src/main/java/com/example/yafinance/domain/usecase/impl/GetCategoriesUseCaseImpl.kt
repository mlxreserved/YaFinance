package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.domain.repositories.CategoryRepository
import com.example.yafinance.domain.usecase.inter.GetCategoriesUseCase
import com.example.yafinance.domain.utils.Result
import javax.inject.Inject

class GetCategoriesUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : GetCategoriesUseCase {
    override suspend fun getCategories(): Result<List<Category>> =
        categoryRepository.getCategories()
}