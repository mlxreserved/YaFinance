package com.example.yafinance.domain.repositories

import com.example.yafinance.domain.utils.Result
import com.example.yafinance.domain.models.account.Account


interface AccountRepository {

    suspend fun getAccount(): Result<Account>


    suspend fun changeAccountInfo(id: Int, accountRequest: Account): Result<Account>


    suspend fun getAccountId(): Result<Int>
}