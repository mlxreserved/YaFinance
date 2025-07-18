package com.example.workmanager.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards FinanceWorkerFactory>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val clazz = Class.forName(workerClassName).asSubclass(ListenableWorker::class.java)
        val factory = workerFactories[clazz]
            ?: throw IllegalArgumentException("Unknown worker class: $workerClassName")
        return factory.create(appContext, workerParameters)
    }
}