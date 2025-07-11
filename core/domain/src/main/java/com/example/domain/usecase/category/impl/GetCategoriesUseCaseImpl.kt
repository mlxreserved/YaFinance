package com.example.domain.usecase.category.impl

import com.example.domain.model.category.Category
import com.example.domain.repository.category.CategoryRepository
import com.example.domain.usecase.category.inter.GetCategoriesUseCase
import com.example.model.result.Result

class GetCategoriesUseCaseImpl (
    private val categoryRepository: CategoryRepository
) : GetCategoriesUseCase {
    override suspend fun getCategories(): Result<List<Category>> =
        categoryRepository.getCategories()
}