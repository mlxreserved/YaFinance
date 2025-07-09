package com.example.expense.di.module

import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.usecase.expense.impl.GetExpensesUseCaseImpl
import com.example.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.expense.di.scope.ExpenseScope
import dagger.Module
import dagger.Provides

@Module
object ExpenseUseCaseModule {
    @ExpenseScope
    @Provides
    fun provideGetExpensesUseCase(
        expenseRepository: ExpenseRepository
    ): GetExpensesUseCase =
        GetExpensesUseCaseImpl(expenseRepository)
}