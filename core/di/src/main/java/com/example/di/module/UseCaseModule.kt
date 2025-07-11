package com.example.di.module

import com.example.domain.repository.account.AccountRepository
import com.example.domain.repository.global.AccountNameProvider
import com.example.domain.repository.global.BalanceProvider
import com.example.domain.repository.global.CurrencyProvider
import com.example.domain.usecase.account.impl.GetAccountIdUseCaseImpl
import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.domain.usecase.global.impl.GetCurrentAccountNameUseCaseImpl
import com.example.domain.usecase.global.impl.GetCurrentBalanceUseCaseImpl
import com.example.domain.usecase.global.impl.GetCurrentCurrencyUseCaseImpl
import com.example.domain.usecase.global.impl.SetCurrentAccountNameUseCaseImpl
import com.example.domain.usecase.global.impl.SetCurrentBalanceUseCaseImpl
import com.example.domain.usecase.global.impl.SetCurrentCurrencyUseCaseImpl
import com.example.domain.usecase.global.inter.GetCurrentAccountNameUseCase
import com.example.domain.usecase.global.inter.GetCurrentBalanceUseCase
import com.example.domain.usecase.global.inter.GetCurrentCurrencyUseCase
import com.example.domain.usecase.global.inter.SetCurrentAccountNameUseCase
import com.example.domain.usecase.global.inter.SetCurrentBalanceUseCase
import com.example.domain.usecase.global.inter.SetCurrentCurrencyUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetAccountId(
        accountRepository: AccountRepository
    ): GetAccountIdUseCase =
        GetAccountIdUseCaseImpl(accountRepository)


    @Singleton
    @Provides
    fun provideGetCurrency(
        currencyProvider: CurrencyProvider
    ): GetCurrentCurrencyUseCase =
        GetCurrentCurrencyUseCaseImpl(currencyProvider)

    @Singleton
    @Provides
    fun provideGetAccountName(
        accountNameProvider: AccountNameProvider
    ): GetCurrentAccountNameUseCase =
        GetCurrentAccountNameUseCaseImpl(accountNameProvider)

    @Singleton
    @Provides
    fun provideGetBalance(
        balanceProvider: BalanceProvider
    ): GetCurrentBalanceUseCase =
        GetCurrentBalanceUseCaseImpl(balanceProvider)

    @Singleton
    @Provides
    fun provideSetCurrency(
        currencyProvider: CurrencyProvider
    ): SetCurrentCurrencyUseCase =
        SetCurrentCurrencyUseCaseImpl(currencyProvider)

    @Singleton
    @Provides
    fun provideSetAccountName(
        accountNameProvider: AccountNameProvider
    ): SetCurrentAccountNameUseCase =
        SetCurrentAccountNameUseCaseImpl(accountNameProvider)

    @Singleton
    @Provides
    fun provideSetBalance(
        balanceProvider: BalanceProvider
    ): SetCurrentBalanceUseCase =
        SetCurrentBalanceUseCaseImpl(balanceProvider)
}