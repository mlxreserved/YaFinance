package com.example.data.repository.category

import com.example.data.mappers.toDomain
import com.example.domain.model.category.Category
import com.example.domain.repository.category.CategoryRepository
import com.example.model.result.Result
import com.example.network.api.CategoryApi
import com.example.network.utils.safeCall.safeCallWithRetry
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