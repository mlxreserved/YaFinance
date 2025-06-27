package com.example.yafinance.data.remote.api

import com.example.yafinance.data.remote.models.category.CategoryDTO
import com.example.yafinance.data.remote.utils.FinanceUrls.CATEGORIES
import retrofit2.http.GET

interface CategoryApi {
    @GET(CATEGORIES)
    suspend fun getCategories(): List<CategoryDTO>
}