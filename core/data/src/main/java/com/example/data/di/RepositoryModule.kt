package com.example.data.di

import com.example.data.repository.account.AccountRepositoryImpl
import com.example.data.repository.category.CategoryRepositoryImpl
import com.example.data.repository.expense.ExpenseRepositoryImpl
import com.example.data.repository.global.NetworkMonitorImpl
import com.example.data.repository.income.IncomeRepositoryImpl
import com.example.domain.repository.account.AccountRepository
import com.example.domain.repository.category.CategoryRepository
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.repository.global.NetworkMonitor
import com.example.domain.repository.income.IncomeRepository
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

    @Binds
    @Singleton
    fun bindIncomeRepository(
        repository: IncomeRepositoryImpl
    ): IncomeRepository

    @Binds
    @Singleton
    fun bindExpenseRepository(
        repository: ExpenseRepositoryImpl
    ): ExpenseRepository
}