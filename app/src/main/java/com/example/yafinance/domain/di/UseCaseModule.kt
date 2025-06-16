package com.example.yafinance.domain.di

import com.example.yafinance.domain.usecase.impl.GetAccountsUseCaseImpl
import com.example.yafinance.domain.usecase.inter.GetAccountsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindGetAccounts(
        implementation: GetAccountsUseCaseImpl
    ): GetAccountsUseCase
}