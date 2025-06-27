package com.example.yafinance

import android.app.Application
import android.content.Context
import com.example.yafinance.di.AppComponent
import com.example.yafinance.di.DaggerAppComponent

class FinanceApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is FinanceApplication -> appComponent
        else -> applicationContext.appComponent
    }