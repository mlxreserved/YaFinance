package com.example.yafinance.domain.usecase.inter

import com.example.yafinance.domain.models.account.Account

interface GetAccountsUseCase {
    suspend fun getAccounts(): List<Account>
}