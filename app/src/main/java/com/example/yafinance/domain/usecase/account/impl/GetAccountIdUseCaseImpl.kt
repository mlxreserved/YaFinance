package com.example.yafinance.domain.usecase.account.impl

import com.example.yafinance.domain.repositories.account.AccountRepository
import com.example.yafinance.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.yafinance.domain.utils.Result
import javax.inject.Inject

class GetAccountIdUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : GetAccountIdUseCase {
    override suspend fun getAccountId(): Result<Int> =
        accountRepository.getAccountId()
}