package com.example.domain.usecase.account.impl

import com.example.domain.model.account.Account
import com.example.domain.repository.account.AccountRepository
import com.example.domain.usecase.account.inter.ChangeAccountInfoUseCase
import com.example.model.result.Result

class ChangeAccountInfoUseCaseImpl (
    private val accountRepository: AccountRepository
) : ChangeAccountInfoUseCase {
    override suspend fun changeAccountInfo(
        id: Int,
        accountRequest: Account
    ): Result<Account> = accountRepository.changeAccountInfo(id, accountRequest)
}