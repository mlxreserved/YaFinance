package com.example.yafinance.data.local.di

import android.content.Context
import com.example.yafinance.data.local.dataSources.impl.AccountLocalDataSourceImpl
import com.example.yafinance.data.local.dataSources.inter.AccountLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideAccountLocalDataSource(@ApplicationContext context: Context): AccountLocalDataSource =
        AccountLocalDataSourceImpl(context = context)

}