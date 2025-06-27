package com.example.yafinance.data.remote.repositories

import com.example.yafinance.data.remote.api.CategoryApi
import com.example.yafinance.data.remote.mappers.toDomain
import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.domain.repositories.CategoryRepository
import kotlinx.coroutines.Dispatchers
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.data.remote.utils.safeCallWithRetry
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