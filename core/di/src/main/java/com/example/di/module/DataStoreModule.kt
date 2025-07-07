package com.example.di.module

import com.example.datastore.AccountDataStoreImpl
import com.example.domain.repository.account.AccountDataStore
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataStoreModule {
    @Singleton
    @Binds
    abstract fun bindAccountDataStore(
        repository: AccountDataStoreImpl
    ): AccountDataStore
}