package com.example.yafinance.data.remote.repositories.category

import com.example.yafinance.data.remote.api.CategoryApi
import com.example.yafinance.data.remote.mappers.toDomain
import com.example.yafinance.data.remote.utils.safeCallWithRetry
import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.domain.repositories.category.CategoryRepository
import com.example.yafinance.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/** Репозиторий для работы с категориями **/
class CategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi
) : CategoryRepository {
    /** Получить список всех категорий **/
    override suspend fun getCategories(): Result<List<Category>> = safeCallWithRetry {
        withContext(Dispatchers.IO) {
            categoryApi.getCategories().map { category -> category.toDomain() }
        }
    }
}