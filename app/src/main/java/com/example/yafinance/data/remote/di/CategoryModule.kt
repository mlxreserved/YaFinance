package com.example.yafinance.data.remote.di

import com.example.yafinance.data.remote.api.FinanceApi
import com.example.yafinance.data.remote.repositories.CategoryRepositoryImpl
import com.example.yafinance.domain.repositories.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {
    @Singleton
    @Provides
    fun provideCategoryRepository(
        financeApi: FinanceApi
    ): CategoryRepository =
        CategoryRepositoryImpl(
            financeApi = financeApi
        )

}