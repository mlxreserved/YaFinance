package com.example.income.di.module

import com.example.data.repository.income.IncomeRepositoryImpl
import com.example.domain.repository.income.IncomeRepository
import com.example.income.di.scope.IncomeScope
import dagger.Binds
import dagger.Module

@Module
abstract class IncomeModule {
    @Binds
    @IncomeScope
    abstract fun bindIncomeRepository(
        repository: IncomeRepositoryImpl
    ): IncomeRepository
}