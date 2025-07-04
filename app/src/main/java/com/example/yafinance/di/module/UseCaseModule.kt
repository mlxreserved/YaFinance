package com.example.yafinance.di.module

import com.example.yafinance.domain.usecase.account.impl.ChangeAccountInfoUseCaseImpl
import com.example.yafinance.domain.usecase.account.impl.GetAccountIdUseCaseImpl
import com.example.yafinance.domain.usecase.account.impl.GetAccountUseCaseImpl
import com.example.yafinance.domain.usecase.category.impl.GetCategoriesUseCaseImpl
import com.example.yafinance.domain.usecase.global.impl.GetCurrentCurrencyUseCaseImpl
import com.example.yafinance.domain.usecase.expense.impl.GetExpensesUseCaseImpl
import com.example.yafinance.domain.usecase.income.impl.GetIncomesUseCaseImpl
import com.example.yafinance.domain.usecase.global.impl.SetCurrentCurrencyUseCaseImpl
import com.example.yafinance.domain.usecase.account.inter.ChangeAccountInfoUseCase
import com.example.yafinance.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.yafinance.domain.usecase.account.inter.GetAccountUseCase
import com.example.yafinance.domain.usecase.category.inter.GetCategoriesUseCase
import com.example.yafinance.domain.usecase.global.inter.GetCurrentCurrencyUseCase
import com.example.yafinance.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.yafinance.domain.usecase.global.impl.GetCurrentAccountNameUseCaseImpl
import com.example.yafinance.domain.usecase.global.impl.GetCurrentBalanceUseCaseImpl
import com.example.yafinance.domain.usecase.global.impl.SetCurrentAccountNameUseCaseImpl
import com.example.yafinance.domain.usecase.global.impl.SetCurrentBalanceUseCaseImpl
import com.example.yafinance.domain.usecase.global.inter.GetCurrentAccountNameUseCase
import com.example.yafinance.domain.usecase.global.inter.GetCurrentBalanceUseCase
import com.example.yafinance.domain.usecase.global.inter.SetCurrentAccountNameUseCase
import com.example.yafinance.domain.usecase.global.inter.SetCurrentBalanceUseCase
import com.example.yafinance.domain.usecase.income.inter.GetIncomesUseCase
import com.example.yafinance.domain.usecase.global.inter.SetCurrentCurrencyUseCase
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

    @Binds
    @Singleton
    abstract fun bindSetCurrency(
        implementation: SetCurrentCurrencyUseCaseImpl
    ): SetCurrentCurrencyUseCase

    @Binds
    @Singleton
    abstract fun bindGetCurrency(
        implementation: GetCurrentCurrencyUseCaseImpl
    ): GetCurrentCurrencyUseCase

    @Binds
    @Singleton
    abstract fun bindGetAccountName(
        implementation: GetCurrentAccountNameUseCaseImpl
    ): GetCurrentAccountNameUseCase

    @Binds
    @Singleton
    abstract fun bindSetAccountName(
        implementation: SetCurrentAccountNameUseCaseImpl
    ): SetCurrentAccountNameUseCase

    @Binds
    @Singleton
    abstract fun bindGetBalance(
        implementation: GetCurrentBalanceUseCaseImpl
    ): GetCurrentBalanceUseCase

    @Binds
    @Singleton
    abstract fun bindSetBalance(
        implementation: SetCurrentBalanceUseCaseImpl
    ): SetCurrentBalanceUseCase
}