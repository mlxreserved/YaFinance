package com.example.yafinance.di.module

import com.example.yafinance.data.remote.repositories.AccountRepositoryImpl
import com.example.yafinance.data.remote.repositories.CategoryRepositoryImpl
import com.example.yafinance.data.remote.repositories.NetworkMonitorImpl
import com.example.yafinance.data.remote.repositories.TransactionRepositoryImpl
import com.example.yafinance.domain.repositories.AccountRepository
import com.example.yafinance.domain.repositories.CategoryRepository
import com.example.yafinance.domain.repositories.NetworkMonitor
import com.example.yafinance.domain.repositories.TransactionRepository
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
}