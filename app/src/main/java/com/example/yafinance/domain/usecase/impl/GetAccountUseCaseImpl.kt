package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.repositories.AccountRepository
import com.example.yafinance.domain.usecase.inter.GetAccountUseCase
import com.example.yafinance.domain.utils.Result
import javax.inject.Inject

class GetAccountUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : GetAccountUseCase {
    override suspend fun getAccounts(): Result<Account> = accountRepository.getAccount()
}