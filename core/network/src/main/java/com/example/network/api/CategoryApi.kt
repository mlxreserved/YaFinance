package com.example.network.api

import com.example.network.dto.category.CategoryDTO
import com.example.network.utils.FinanceUrls.CATEGORIES
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApi {
    @GET(CATEGORIES)
    suspend fun getCategories(): List<CategoryDTO>

    @GET("$CATEGORIES/type/{isIncome}")
    suspend fun getCategoriesByType(
        @Path("isIncome") isIncome: Boolean
    ): List<CategoryDTO>
}