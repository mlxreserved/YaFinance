package com.example.yafinance.domain.usecase.global.impl

import com.example.yafinance.domain.repositories.global.AccountNameProvider
import com.example.yafinance.domain.usecase.global.inter.GetCurrentAccountNameUseCase
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCurrentAccountNameUseCaseImpl @Inject constructor(
    private val accountNameProvider: AccountNameProvider
) : GetCurrentAccountNameUseCase {
    override fun getAccountName(): StateFlow<String> = accountNameProvider.currentAccountName
}