package com.example.yafinance.di.module

import com.example.yafinance.data.local.dataSources.impl.AccountLocalDataSourceImpl
import com.example.yafinance.data.local.dataSources.inter.AccountLocalDataSource
import com.example.yafinance.data.remote.dataSources.impl.AccountRemoteDataSourceImpl
import com.example.yafinance.data.remote.dataSources.inter.AccountRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataSourceModule {
    @Singleton
    @Binds
    fun bindAccountRemoteDataSource(
        dataSource: AccountRemoteDataSourceImpl
    ): AccountRemoteDataSource

    @Singleton
    @Binds
    fun bindAccountLocalDataSource(
        dataSource: AccountLocalDataSourceImpl
    ): AccountLocalDataSource
}