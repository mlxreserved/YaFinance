package com.example.di.module

import com.example.domain.repository.account.AccountRepository
import com.example.domain.repository.category.CategoryRepository
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.account.impl.GetAccountIdUseCaseImpl
import com.example.domain.usecase.account.impl.SyncLocalChangesAccountUseCaseImpl
import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.domain.usecase.account.inter.SyncLocalChangesAccountUseCase
import com.example.domain.usecase.category.impl.GetCategoriesUseCaseImpl
import com.example.domain.usecase.category.inter.GetCategoriesUseCase
import com.example.domain.usecase.expense.impl.SyncLocalChangesExpenseUseCaseImpl
import com.example.domain.usecase.expense.inter.SyncLocalChangesExpenseUseCase
import com.example.domain.usecase.income.impl.SyncLocalChangesIncomesUseCaseImpl
import com.example.domain.usecase.income.inter.SyncLocalChangesIncomesUseCase
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
    fun provideGetCategoriesUseCase(
        categoryRepository: CategoryRepository
    ): GetCategoriesUseCase =
        GetCategoriesUseCaseImpl(categoryRepository)

    @Singleton
    @Provides
    fun provideSyncLocalChangesIncomes(
        incomeRepository: IncomeRepository
    ): SyncLocalChangesIncomesUseCase =
        SyncLocalChangesIncomesUseCaseImpl(incomeRepository)

    @Singleton
    @Provides
    fun provideSyncLocalChangesExpenses(
        expenseRepository: ExpenseRepository
    ): SyncLocalChangesExpenseUseCase =
        SyncLocalChangesExpenseUseCaseImpl(expenseRepository)

    @Singleton
    @Provides
    fun provideSyncLocalChangesAccount(
        accountRepository: AccountRepository
    ): SyncLocalChangesAccountUseCase =
        SyncLocalChangesAccountUseCaseImpl(accountRepository)
}