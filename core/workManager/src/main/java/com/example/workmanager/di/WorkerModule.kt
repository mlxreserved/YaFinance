package com.example.workmanager.di

import com.example.workmanager.worker.FinanceWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(FinanceWorker::class)
    fun bindFinanceWorkerFactory(factory: FinanceWorker.Factory): FinanceWorkerFactory
}