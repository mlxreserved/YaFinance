package com.example.yafinance.domain.usecase.account.impl

import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.repositories.account.AccountRepository
import com.example.yafinance.domain.usecase.account.inter.GetAccountUseCase
import com.example.yafinance.domain.utils.Result
import javax.inject.Inject

class GetAccountUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : GetAccountUseCase {
    override suspend fun getAccounts(): Result<Account> = accountRepository.getAccount()
}