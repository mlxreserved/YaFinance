package com.example.yafinance.di.module

import com.example.yafinance.data.remote.repositories.account.AccountRepositoryImpl
import com.example.yafinance.data.remote.repositories.category.CategoryRepositoryImpl
import com.example.yafinance.data.remote.repositories.global.AccountNameProviderImpl
import com.example.yafinance.data.remote.repositories.global.BalanceProviderImpl
import com.example.yafinance.data.remote.repositories.global.CurrencyManager
import com.example.yafinance.data.remote.repositories.global.NetworkMonitorImpl
import com.example.yafinance.data.remote.repositories.transaction.TransactionRepositoryImpl
import com.example.yafinance.domain.repositories.account.AccountRepository
import com.example.yafinance.domain.repositories.category.CategoryRepository
import com.example.yafinance.domain.repositories.global.AccountNameProvider
import com.example.yafinance.domain.repositories.global.BalanceProvider
import com.example.yafinance.domain.repositories.global.CurrencyProvider
import com.example.yafinance.domain.repositories.global.NetworkMonitor
import com.example.yafinance.domain.repositories.transaction.TransactionRepository
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
    fun bindTransactionRepository(
        repository: TransactionRepositoryImpl
    ): TransactionRepository

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