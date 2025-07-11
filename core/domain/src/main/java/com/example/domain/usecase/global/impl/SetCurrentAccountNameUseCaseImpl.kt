package com.example.domain.usecase.global.impl

import com.example.domain.repository.global.AccountNameProvider
import com.example.domain.usecase.global.inter.SetCurrentAccountNameUseCase

class SetCurrentAccountNameUseCaseImpl (
    private val accountNameProvider: AccountNameProvider
) : SetCurrentAccountNameUseCase {
    override fun setAccountName(newAccountName: String) =
        accountNameProvider.setAccountName(newAccountName)
}