package com.example.yafinance.data.remote.di

import com.example.yafinance.data.local.dataSources.inter.AccountLocalDataSource
import com.example.yafinance.data.remote.api.FinanceApi
import com.example.yafinance.data.remote.dataSources.impl.AccountRemoteDataSourceImpl
import com.example.yafinance.data.remote.dataSources.inter.AccountRemoteDataSource
import com.example.yafinance.data.remote.repositories.AccountRepositoryImpl
import com.example.yafinance.domain.repositories.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {
    @Singleton
    @Provides
    fun provideAccountRepository(
        accountLocalDataSource: AccountLocalDataSource,
        accountRemoteDataSource: AccountRemoteDataSource
    ): AccountRepository =
        AccountRepositoryImpl(
            accountLocalDataSource = accountLocalDataSource,
            accountRemoteDataSource = accountRemoteDataSource
        )

    @Singleton
    @Provides
    fun provideAccountRemoteDataSource(financeApi: FinanceApi): AccountRemoteDataSource =
        AccountRemoteDataSourceImpl(financeApi = financeApi)

}