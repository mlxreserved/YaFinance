package com.example.yafinance.domain.repositories

import com.example.yafinance.domain.models.account.Account

interface AccountRepository {

    suspend fun getAccounts(): List<Account>

}