package com.example.domain.usecase.account.impl

import com.example.domain.model.account.Account
import com.example.domain.repository.account.AccountRepository
import com.example.domain.usecase.account.inter.GetAccountUseCase
import com.example.model.result.Result

class GetAccountUseCaseImpl (
    private val accountRepository: AccountRepository
) : GetAccountUseCase {
    override suspend fun getAccounts(): Result<Account> = accountRepository.getAccount()
}