package com.example.yafinance.data.remote.repositories

import com.example.yafinance.data.remote.api.FinanceApi
import com.example.yafinance.data.remote.mappers.toDomain
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.repositories.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val financeApi: FinanceApi
) : AccountRepository {

    override suspend fun getAccounts(): List<Account> =
        withContext(Dispatchers.IO) {
            financeApi.getAccounts().map { it.toDomain() }
        }

}