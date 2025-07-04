package com.example.yafinance.domain.usecase.global.impl

import com.example.yafinance.domain.repositories.global.AccountNameProvider
import com.example.yafinance.domain.usecase.global.inter.SetCurrentAccountNameUseCase
import javax.inject.Inject

class SetCurrentAccountNameUseCaseImpl @Inject constructor(
    private val accountNameProvider: AccountNameProvider
) : SetCurrentAccountNameUseCase {
    override fun setAccountName(newAccountName: String) =
        accountNameProvider.setAccountName(newAccountName)
}