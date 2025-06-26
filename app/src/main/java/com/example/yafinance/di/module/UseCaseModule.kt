package com.example.yafinance.di.module

import com.example.yafinance.domain.usecase.impl.ChangeAccountInfoUseCaseImpl
import com.example.yafinance.domain.usecase.impl.GetAccountIdUseCaseImpl
import com.example.yafinance.domain.usecase.impl.GetAccountUseCaseImpl
import com.example.yafinance.domain.usecase.impl.GetCategoriesUseCaseImpl
import com.example.yafinance.domain.usecase.impl.GetExpensesUseCaseImpl
import com.example.yafinance.domain.usecase.impl.GetIncomesUseCaseImpl
import com.example.yafinance.domain.usecase.inter.ChangeAccountInfoUseCase
import com.example.yafinance.domain.usecase.inter.GetAccountIdUseCase
import com.example.yafinance.domain.usecase.inter.GetAccountUseCase
import com.example.yafinance.domain.usecase.inter.GetCategoriesUseCase
import com.example.yafinance.domain.usecase.inter.GetExpensesUseCase
import com.example.yafinance.domain.usecase.inter.GetIncomesUseCase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindGetAccounts(
        implementation: GetAccountUseCaseImpl
    ): GetAccountUseCase

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

    @Binds
    @Singleton
    abstract fun bindGetAccountId(
        implementation: GetAccountIdUseCaseImpl
    ): GetAccountIdUseCase
}