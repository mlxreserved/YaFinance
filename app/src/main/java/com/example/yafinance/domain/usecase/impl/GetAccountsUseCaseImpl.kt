package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.repositories.AccountRepository
import com.example.yafinance.domain.usecase.inter.GetAccountsUseCase
import com.example.yafinance.domain.utils.Result
import javax.inject.Inject

class GetAccountsUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : GetAccountsUseCase {
    override suspend fun getAccounts(): Result<List<Account>> = accountRepository.getAccounts()
}