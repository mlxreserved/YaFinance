package com.example.yafinance.di

import android.content.Context
import com.example.yafinance.di.module.AppModule
import com.example.yafinance.di.module.DataSourceModule
import com.example.yafinance.di.module.NetworkModule
import com.example.yafinance.di.module.RepositoryModule
import com.example.yafinance.di.module.UseCaseModule
import com.example.yafinance.di.module.ViewModelModule
import com.example.yafinance.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataSourceModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

