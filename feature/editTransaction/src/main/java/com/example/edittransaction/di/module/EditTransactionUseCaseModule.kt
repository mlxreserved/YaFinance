package com.example.edittransaction.di.module

import com.example.domain.repository.category.CategoryRepository
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.repository.income.IncomeRepository
import com.example.domain.usecase.category.impl.GetCategoriesByTypeUseCaseImpl
import com.example.domain.usecase.category.inter.GetCategoriesByTypeUseCase
import com.example.domain.usecase.expense.impl.CreateExpenseUseCaseImpl
import com.example.domain.usecase.expense.impl.GetExpenseByIdUseCaseImpl
import com.example.domain.usecase.expense.impl.GetExpensesUseCaseImpl
import com.example.domain.usecase.expense.impl.UpdateExpenseByIdUseCaseImpl
import com.example.domain.usecase.expense.inter.CreateExpenseUseCase
import com.example.domain.usecase.expense.inter.GetExpenseByIdUseCase
import com.example.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.domain.usecase.expense.inter.UpdateExpenseByIdUseCase
import com.example.domain.usecase.income.impl.CreateIncomeUseCaseImpl
import com.example.domain.usecase.income.impl.GetIncomeByIdUseCaseImpl
import com.example.domain.usecase.income.impl.UpdateIncomeByIdUseCaseImpl
import com.example.domain.usecase.income.inter.CreateIncomeUseCase
import com.example.domain.usecase.income.inter.GetIncomeByIdUseCase
import com.example.domain.usecase.income.inter.UpdateIncomeByIdUseCase
import com.example.edittransaction.di.scope.EditTransactionScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object EditTransactionUseCaseModule {
    @EditTransactionScope
    @Provides
    fun provideGetExpenseByIdUseCase(
        expenseRepository: ExpenseRepository
    ): GetExpenseByIdUseCase =
        GetExpenseByIdUseCaseImpl(expenseRepository)

    @EditTransactionScope
    @Provides
    fun provideGetIncomeByIdUseCase(
        incomeRepository: IncomeRepository
    ): GetIncomeByIdUseCase =
        GetIncomeByIdUseCaseImpl(incomeRepository)

    @EditTransactionScope
    @Provides
    fun provideGetCategoryByType(
        categoryRepository: CategoryRepository
    ): GetCategoriesByTypeUseCase =
        GetCategoriesByTypeUseCaseImpl(categoryRepository)

    @EditTransactionScope
    @Provides
    fun provideUpdateExpenseByIdUseCase(
        expenseRepository: ExpenseRepository
    ): UpdateExpenseByIdUseCase =
        UpdateExpenseByIdUseCaseImpl(expenseRepository)

    @EditTransactionScope
    @Provides
    fun provideUpdateIncomeByIdUseCase(
        incomeRepository: IncomeRepository
    ): UpdateIncomeByIdUseCase =
        UpdateIncomeByIdUseCaseImpl(incomeRepository)

    @EditTransactionScope
    @Provides
    fun provideCreateIncomeUseCase(
        incomeRepository: IncomeRepository
    ): CreateIncomeUseCase =
        CreateIncomeUseCaseImpl(incomeRepository)

    @EditTransactionScope
    @Provides
    fun provideCreateExpenseUseCase(
        expenseRepository: ExpenseRepository
    ): CreateExpenseUseCase =
        CreateExpenseUseCaseImpl(expenseRepository)
}