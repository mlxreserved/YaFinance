package com.example.data.di

import com.example.data.repository.account.AccountRepositoryImpl
import com.example.data.repository.category.CategoryRepositoryImpl
import com.example.data.repository.global.AccountNameProviderImpl
import com.example.data.repository.global.BalanceProviderImpl
import com.example.data.repository.global.CurrencyManager
import com.example.data.repository.global.NetworkMonitorImpl
import com.example.domain.repository.account.AccountRepository
import com.example.domain.repository.category.CategoryRepository
import com.example.domain.repository.global.AccountNameProvider
import com.example.domain.repository.global.BalanceProvider
import com.example.domain.repository.global.CurrencyProvider
import com.example.domain.repository.global.NetworkMonitor
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindCategoryRepository(
        repository: CategoryRepositoryImpl
    ): CategoryRepository

    @Singleton
    @Binds
    fun bindAccountRepository(
        repository: AccountRepositoryImpl
    ): AccountRepository

    @Singleton
    @Binds
    fun bindNetworkMonitor(
        repository: NetworkMonitorImpl
    ): NetworkMonitor

    @Singleton
    @Binds
    fun bindCurrencyProvider(
        repository: CurrencyManager
    ): CurrencyProvider

    @Singleton
    @Binds
    fun bindAccountNameProvider(
        repository: AccountNameProviderImpl
    ): AccountNameProvider

    @Singleton
    @Binds
    fun bindBalanceProvider(
        repository: BalanceProviderImpl
    ): BalanceProvider
}