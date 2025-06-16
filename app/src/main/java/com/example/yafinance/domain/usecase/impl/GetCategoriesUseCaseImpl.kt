package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.domain.repositories.CategoryRepository
import com.example.yafinance.domain.usecase.inter.GetCategoriesUseCase
import javax.inject.Inject

class GetCategoriesUseCaseImpl @Inject constructor(private val categoryRepository: CategoryRepository) : GetCategoriesUseCase {
    override suspend fun getCategories(): List<Category> = categoryRepository.getCategories()
}