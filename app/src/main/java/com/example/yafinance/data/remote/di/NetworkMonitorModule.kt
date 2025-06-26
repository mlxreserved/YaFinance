package com.example.yafinance.data.remote.di

import android.content.Context
import com.example.yafinance.data.remote.repositories.NetworkMonitorImpl
import com.example.yafinance.domain.repositories.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkMonitorModule {
    @Singleton
    @Provides
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor =
        NetworkMonitorImpl(
            context = context
        )
}