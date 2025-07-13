package com.example.edittransaction.di.module

import com.example.data.repository.expense.ExpenseRepositoryImpl
import com.example.data.repository.income.IncomeRepositoryImpl
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.repository.income.IncomeRepository
import com.example.edittransaction.di.scope.EditTransactionScope
import dagger.Binds
import dagger.Module

@Module
abstract class EditTransactionModule {
    @Binds
    @EditTransactionScope
    abstract fun bindExpenseRepository(
        repository: ExpenseRepositoryImpl
    ): ExpenseRepository

    @Binds
    @EditTransactionScope
    abstract fun bindIncomeRepository(
        repository: IncomeRepositoryImpl
    ): IncomeRepository
}