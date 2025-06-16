package com.example.yafinance.data.remote.api

import com.example.yafinance.data.remote.models.account.AccountDTO
import com.example.yafinance.data.remote.models.account.AccountRequestDTO
import com.example.yafinance.data.remote.models.category.CategoryDTO
import com.example.yafinance.data.remote.utils.FinanceUrls.ACCOUNTS
import com.example.yafinance.data.remote.utils.FinanceUrls.CATEGORIES
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface FinanceApi {

    @GET(ACCOUNTS)
    suspend fun getAccounts(): List<AccountDTO>

    @GET(CATEGORIES)
    suspend fun getCategories(): List<CategoryDTO>

    @PUT("$ACCOUNTS/{id}")
    suspend fun changeAccountInfo(@Path("id") id: Int, @Body accountRequest: AccountRequestDTO): AccountDTO
}