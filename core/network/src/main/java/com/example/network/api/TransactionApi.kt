package com.example.network.api

import com.example.network.dto.transaction.TransactionDTO
import com.example.network.dto.transaction.request.TransactionRequestDTO
import com.example.network.dto.transaction.response.TransactionResponseDTO
import com.example.network.utils.FinanceUrls.TRANSACTIONS
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @PUT("$TRANSACTIONS/{id}")
    suspend fun updateTransactionById(
        @Path("id") id: Int,
        @Body transactionRequest: TransactionRequestDTO
    ): TransactionResponseDTO

    @DELETE("$TRANSACTIONS/{id}")
    suspend fun deleteTransactionById(
        @Path("id") id: Int
    )

    @POST(TRANSACTIONS)
    suspend fun createTransaction(
        @Body transactionRequest: TransactionRequestDTO
    ): TransactionDTO
}