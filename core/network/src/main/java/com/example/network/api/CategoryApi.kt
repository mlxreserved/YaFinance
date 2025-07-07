package com.example.network.api

import com.example.network.dto.category.CategoryDTO
import com.example.network.utils.FinanceUrls.CATEGORIES
import retrofit2.http.GET

interface CategoryApi {
    @GET(CATEGORIES)
    suspend fun getCategories(): List<CategoryDTO>
}