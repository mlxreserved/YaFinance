package com.example.yafinance.data.remote.api

import com.example.yafinance.data.remote.models.account.AccountDTO
import com.example.yafinance.data.remote.utils.FinanceUrls.ACCOUNTS
import retrofit2.http.GET

interface FinanceApi {

    @GET(ACCOUNTS)
    suspend fun getAccounts(): List<AccountDTO>

}