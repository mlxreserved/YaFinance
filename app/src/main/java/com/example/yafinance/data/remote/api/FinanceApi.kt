package com.example.yafinance.data.remote.api

import com.example.yafinance.data.remote.models.account.AccountDTO
import com.example.yafinance.data.remote.models.account.AccountRequestDTO
import com.example.yafinance.data.remote.models.category.CategoryDTO
import com.example.yafinance.data.remote.models.transaction.response.TransactionResponseDTO
import com.example.yafinance.data.remote.utils.FinanceUrls.ACCOUNTS
import com.example.yafinance.data.remote.utils.FinanceUrls.CATEGORIES
import com.example.yafinance.data.remote.utils.FinanceUrls.TRANSACTIONS
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface FinanceApi {

    @GET(ACCOUNTS)
    suspend fun getAccounts(): List<AccountDTO>

    @GET(CATEGORIES)
    suspend fun getCategories(): List<CategoryDTO>

    @PUT("$ACCOUNTS/{id}")
    suspend fun changeAccountInfo(
        @Path("id") id: Int,
        @Body accountRequest: AccountRequestDTO
    ): AccountDTO

    @GET("$TRANSACTIONS/account/{accountId}/period")
    suspend fun getTransactions(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String?,
        @Query("endDate") endDate: String?
    ): List<TransactionResponseDTO>
}