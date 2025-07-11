package com.example.account.di.module

import com.example.account.di.scope.AccountScope
import com.example.domain.repository.account.AccountRepository
import com.example.domain.usecase.account.impl.ChangeAccountInfoUseCaseImpl
import com.example.domain.usecase.account.impl.GetAccountUseCaseImpl
import com.example.domain.usecase.account.inter.ChangeAccountInfoUseCase
import com.example.domain.usecase.account.inter.GetAccountUseCase
import dagger.Module
import dagger.Provides

@Module
object AccountUseCaseModule {
    @AccountScope
    @Provides
    fun provideGetAccountUseCase(
        accountRepository: AccountRepository
    ): GetAccountUseCase =
        GetAccountUseCaseImpl(accountRepository)

    @AccountScope
    @Provides
    fun provideChangeAccountInfoUseCase(
        accountRepository: AccountRepository
    ): ChangeAccountInfoUseCase =
        ChangeAccountInfoUseCaseImpl(accountRepository)
}