package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.repositories.AccountRepository
import com.example.yafinance.domain.usecase.inter.GetAccountsUseCase
import javax.inject.Inject

class GetAccountsUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : GetAccountsUseCase {
    override suspend fun getAccounts(): List<Account> = accountRepository.getAccounts()
}