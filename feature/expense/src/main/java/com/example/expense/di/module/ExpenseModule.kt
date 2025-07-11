package com.example.expense.di.module

import com.example.data.repository.expense.ExpenseRepositoryImpl
import com.example.domain.repository.expense.ExpenseRepository
import com.example.expense.di.scope.ExpenseScope
import dagger.Binds
import dagger.Module

@Module
abstract class ExpenseModule {
    @Binds
    @ExpenseScope
    abstract fun bindExpenseRepository(
        repository: ExpenseRepositoryImpl
    ): ExpenseRepository
}