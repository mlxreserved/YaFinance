package com.example.domain.usecase.global.impl

import com.example.domain.repository.global.AccountNameProvider
import com.example.domain.usecase.global.inter.GetCurrentAccountNameUseCase
import kotlinx.coroutines.flow.StateFlow

class GetCurrentAccountNameUseCaseImpl (
    private val accountNameProvider: AccountNameProvider
) : GetCurrentAccountNameUseCase {
    override fun getAccountName(): StateFlow<String> = accountNameProvider.currentAccountName
}