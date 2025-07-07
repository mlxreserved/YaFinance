package com.example.domain.usecase.account.impl

import com.example.domain.repository.account.AccountRepository
import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.model.result.Result

class GetAccountIdUseCaseImpl (
    private val accountRepository: AccountRepository
) : GetAccountIdUseCase {
    override suspend fun getAccountId(): Result<Int> =
        accountRepository.getAccountId()
}