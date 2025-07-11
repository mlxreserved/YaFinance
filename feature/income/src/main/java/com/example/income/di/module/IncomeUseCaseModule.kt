package com.example.income.di.module

import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.expense.impl.GetExpenseByIdUseCaseImpl
import com.example.domain.usecase.expense.inter.GetExpenseByIdUseCase
import com.example.domain.usecase.income.impl.GetIncomeByIdUseCaseImpl
import com.example.domain.usecase.income.impl.GetIncomesUseCaseImpl
import com.example.domain.usecase.income.inter.GetIncomeByIdUseCase
import com.example.domain.usecase.income.inter.GetIncomesUseCase
import com.example.income.di.scope.IncomeScope
import dagger.Module
import dagger.Provides

@Module
object IncomeUseCaseModule {
    @IncomeScope
    @Provides
    fun provideGetIncomesUseCase(
        incomeRepository: IncomeRepository
    ): GetIncomesUseCase =
        GetIncomesUseCaseImpl(incomeRepository)

    @IncomeScope
    @Provides
    fun provideGetIncomeByIdUseCase(
        incomeRepository: IncomeRepository
    ): GetIncomeByIdUseCase =
        GetIncomeByIdUseCaseImpl(incomeRepository)
}