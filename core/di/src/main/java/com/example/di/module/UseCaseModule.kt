package com.example.di.module

import com.example.domain.repository.account.AccountRepository
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.usecase.account.impl.GetAccountIdUseCaseImpl
import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.domain.usecase.expense.impl.GetExpensesUseCaseImpl
import com.example.domain.usecase.expense.inter.GetExpensesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UseCaseModule {
    //    @Binds
//    @Singleton
//    abstract fun bindGetAccounts(
//        implementation: GetAccountUseCaseImpl
//    ): GetAccountUseCase
//
//    @Binds
//    @Singleton
//    abstract fun bindGetCategories(
//        implementation: GetCategoriesUseCaseImpl
//    ): GetCategoriesUseCase
//
//    @Binds
//    @Singleton
//    abstract fun bindChangeAccountInfo(
//        implementation: ChangeAccountInfoUseCaseImpl
//    ): ChangeAccountInfoUseCase
//

//
//    @Binds
//    @Singleton
//    abstract fun bindGetIncomes(
//        implementation: GetIncomesUseCaseImpl
//    ): GetIncomesUseCase

    @Singleton
    @Provides
    fun provideGetAccountId(
        accountRepository: AccountRepository
    ): GetAccountIdUseCase =
        GetAccountIdUseCaseImpl(accountRepository)


//    @Binds
//    @Singleton
//    abstract fun bindSetCurrency(
//        implementation: SetCurrentCurrencyUseCaseImpl
//    ): SetCurrentCurrencyUseCase
//
//    @Binds
//    @Singleton
//    abstract fun bindGetCurrency(
//        implementation: GetCurrentCurrencyUseCaseImpl
//    ): GetCurrentCurrencyUseCase
//
//    @Binds
//    @Singleton
//    abstract fun bindGetAccountName(
//        implementation: GetCurrentAccountNameUseCaseImpl
//    ): GetCurrentAccountNameUseCase
//
//    @Binds
//    @Singleton
//    abstract fun bindSetAccountName(
//        implementation: SetCurrentAccountNameUseCaseImpl
//    ): SetCurrentAccountNameUseCase
//
//    @Binds
//    @Singleton
//    abstract fun bindGetBalance(
//        implementation: GetCurrentBalanceUseCaseImpl
//    ): GetCurrentBalanceUseCase
//
//    @Binds
//    @Singleton
//    abstract fun bindSetBalance(
//        implementation: SetCurrentBalanceUseCaseImpl
//    ): SetCurrentBalanceUseCase
}