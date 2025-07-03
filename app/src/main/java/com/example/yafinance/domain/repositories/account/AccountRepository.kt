package com.example.yafinance.domain.repositories.account

import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.utils.Result

interface AccountRepository {
    suspend fun getAccount(): Result<Account>

    suspend fun changeAccountInfo(id: Int, accountRequest: Account): Result<Account>

    suspend fun getAccountId(): Result<Int>
}