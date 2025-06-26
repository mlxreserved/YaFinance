package com.example.yafinance.data.remote.di

import com.example.yafinance.data.remote.api.FinanceApi
import com.example.yafinance.data.remote.repositories.TransactionRepositoryImpl
import com.example.yafinance.domain.repositories.TransactionRepository
import com.example.yafinance.domain.usecase.inter.GetAccountIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionModule {
    @Singleton
    @Provides
    fun provideTransactionRepository(
        financeApi: FinanceApi,
        getAccountIdUseCase: GetAccountIdUseCase
    ): TransactionRepository =
        TransactionRepositoryImpl(
            financeApi = financeApi,
            getAccountIdUseCase = getAccountIdUseCase
        )
}