package com.example.network.api

import com.example.network.dto.account.AccountDTO
import com.example.network.dto.account.AccountRequestDTO
import com.example.network.utils.FinanceUrls.ACCOUNTS
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface AccountApi {
    @GET(ACCOUNTS)
    suspend fun getAccounts(): List<AccountDTO>

    @PUT("$ACCOUNTS/{id}")
    suspend fun changeAccountInfo(
        @Path("id") id: Int,
        @Body accountRequest: AccountRequestDTO
    ): AccountDTO
}