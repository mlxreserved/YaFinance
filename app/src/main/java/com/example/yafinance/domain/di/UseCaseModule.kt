package com.example.yafinance.domain.di

import com.example.yafinance.domain.usecase.impl.ChangeAccountInfoUseCaseImpl
import com.example.yafinance.domain.usecase.impl.GetAccountsUseCaseImpl
import com.example.yafinance.domain.usecase.impl.GetCategoriesUseCaseImpl
import com.example.yafinance.domain.usecase.impl.GetExpensesUseCaseImpl
import com.example.yafinance.domain.usecase.impl.GetIncomesUseCaseImpl
import com.example.yafinance.domain.usecase.inter.ChangeAccountInfoUseCase
import com.example.yafinance.domain.usecase.inter.GetAccountsUseCase
import com.example.yafinance.domain.usecase.inter.GetCategoriesUseCase
import com.example.yafinance.domain.usecase.inter.GetExpensesUseCase
import com.example.yafinance.domain.usecase.inter.GetIncomesUseCase
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

    @Binds
    @Singleton
    abstract fun bindGetCategories(
        implementation: GetCategoriesUseCaseImpl
    ): GetCategoriesUseCase

    @Binds
    @Singleton
    abstract fun bindChangeAccountInfo(
        implementation: ChangeAccountInfoUseCaseImpl
    ): ChangeAccountInfoUseCase

    @Binds
    @Singleton
    abstract fun bindGetExpenses(
        implementation: GetExpensesUseCaseImpl
    ): GetExpensesUseCase

    @Binds
    @Singleton
    abstract fun bindGetIncomes(
        implementation: GetIncomesUseCaseImpl
    ): GetIncomesUseCase
}