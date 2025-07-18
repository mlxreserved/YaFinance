package com.example.domain.repository.account

import com.example.domain.model.account.Account
import com.example.model.result.Result

interface AccountRepository {
    suspend fun getAccount(): Result<Account>

    suspend fun changeAccountInfo(id: Int, accountRequest: Account): Result<Account>

    suspend fun getAccountId(): Result<Int>

    suspend fun syncLocalChanges()
}