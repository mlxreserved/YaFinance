package com.example.yafinance.domain.usecase.impl

import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.repositories.AccountRepository
import com.example.yafinance.domain.usecase.inter.ChangeAccountInfoUseCase
import com.example.yafinance.domain.utils.Result
import javax.inject.Inject

class ChangeAccountInfoUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : ChangeAccountInfoUseCase {
    override suspend fun changeAccountInfo(
        id: Int,
        accountRequest: Account
    ): Result<Account> = accountRepository.changeAccountInfo(id, accountRequest)
}