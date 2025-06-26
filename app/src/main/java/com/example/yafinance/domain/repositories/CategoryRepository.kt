package com.example.yafinance.domain.repositories

import com.example.yafinance.domain.utils.Result
import com.example.yafinance.domain.models.category.Category

/**
 * Репозиторий для работы с категориями
 **/
interface CategoryRepository {
    /** Получить список всех категорий **/
    suspend fun getCategories(): Result<List<Category>>
}