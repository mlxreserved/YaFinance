package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.repositories.AccountRepository
import com.example.yafinance.domain.usecase.inter.GetAccountIdUseCase
import com.example.yafinance.domain.utils.Result
import javax.inject.Inject

class GetAccountIdUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : GetAccountIdUseCase {
    override suspend fun getAccountId(): Result<Int> =
        accountRepository.getAccountId()

}