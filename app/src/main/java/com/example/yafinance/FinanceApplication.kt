package com.example.yafinance

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import com.example.workmanager.di.DaggerWorkerFactory
import com.example.yafinance.di.AppComponent
import com.example.yafinance.di.DaggerAppComponent
import javax.inject.Inject

class FinanceApplication : Application(), Configuration.Provider {
    lateinit var appComponent: AppComponent

    @Inject lateinit var workerFactory: DaggerWorkerFactory

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is FinanceApplication -> appComponent
        else -> applicationContext.appComponent
    }