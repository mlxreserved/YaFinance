package com.example.network.api

import com.example.network.dto.transaction.response.TransactionResponseDTO
import com.example.network.utils.FinanceUrls.TRANSACTIONS
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

    @GET("$TRANSACTIONS/{id}")
    suspend fun getTransactionById(
        @Path("id") id: Int
    ): TransactionResponseDTO
}