package com.example.data.repository.category

import com.example.data.mappers.toDomain
import com.example.data.mappers.toEntity
import com.example.database.dao.CategoryDao
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
    private val categoryApi: CategoryApi,
    private val categoryDao: CategoryDao
) : CategoryRepository {
    /** Получить список всех категорий **/
    override suspend fun getCategories(): Result<List<Category>> {
        categoryDao.getCategories()
            .map { category -> category.toDomain() }
            .also { categories ->
                if(categories.isNotEmpty()){
                    return Result.Success(categories)
                }
            }

        return safeCallWithRetry {
            withContext(Dispatchers.IO) {
                categoryApi.getCategories().map { category -> category.toDomain() }
            }.also { categories ->
                categoryDao.insertAllCategories(categories.map { category -> category.toEntity() })
            }
        }
    }

    override suspend fun getCategoriesByType(isIncome: Boolean): Result<List<Category>> {
        categoryDao.getCategoriesByType(isIncome)
            .map { category -> category.toDomain() }
            .also { categories ->
                if(categories.isNotEmpty()) {
                    return Result.Success(categories)
                }
            }

        return safeCallWithRetry {
            withContext(Dispatchers.IO) {
                categoryApi.getCategoriesByType(isIncome).map { category -> category.toDomain() }
            }.also { categories ->
                categoryDao.insertAllCategories(categories.map { category -> category.toEntity() })
            }
        }
    }
}