package com.example.domain.usecase.account.impl

import com.example.domain.repository.account.AccountRepository
import com.example.domain.usecase.account.inter.SyncLocalChangesAccountUseCase

class SyncLocalChangesAccountUseCaseImpl(
    private val accountRepository: AccountRepository
) : SyncLocalChangesAccountUseCase {
    override suspend fun syncLocalChanges() =
        accountRepository.syncLocalChanges()
}