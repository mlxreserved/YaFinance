package com.example.yafinance.domain.usecase.inter

import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.utils.Result

interface GetAccountsUseCase {
    suspend fun getAccounts(): Result<List<Account>>
}