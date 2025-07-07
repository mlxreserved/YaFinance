package com.example.expense.internal.di.module

import com.example.data.repository.expense.ExpenseRepositoryImpl
import com.example.expense.internal.di.scope.ExpenseScope
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.domain.usecase.expense.impl.GetExpensesUseCaseImpl
import com.example.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.network.api.TransactionApi
import dagger.Module
import dagger.Provides

@Module
object ExpenseModule {
    @ExpenseScope
    @Provides
    fun provideExpenseRepository(
        transactionApi: TransactionApi,
        getAccountIdUseCase: GetAccountIdUseCase
    ): ExpenseRepository = ExpenseRepositoryImpl(transactionApi, getAccountIdUseCase)

    @Provides
    fun provideGetExpensesUseCase(
        expenseRepository: ExpenseRepository
    ): GetExpensesUseCase =
        GetExpensesUseCaseImpl(expenseRepository)
}