package com.example.yafinance.data.remote.api

import com.example.yafinance.data.remote.models.transaction.response.TransactionResponseDTO
import com.example.yafinance.data.remote.utils.FinanceUrls.TRANSACTIONS
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TransactionApi {
    @GET("$TRANSACTIONS/account/{accountId}/period")
    suspend fun getTransactions(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String?,
        @Query("endDate") endDate: String?
    ): List<TransactionResponseDTO>
}