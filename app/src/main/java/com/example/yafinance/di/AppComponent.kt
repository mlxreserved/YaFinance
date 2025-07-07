package com.example.yafinance.di

import android.content.Context
import com.example.di.component.CoreDependencies
import com.example.di.module.DataStoreModule
import com.example.di.module.ViewModelModule
import com.example.expense.internal.di.component.ExpenseComponent
import com.example.utils.qualifiers.ApplicationContext
import com.example.yafinance.di.module.AppModule
import com.example.yafinance.di.module.DataSourceModule
import com.example.yafinance.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataSourceModule::class,
        com.example.network.di.NetworkModule::class,
        com.example.di.module.UseCaseModule::class,
        com.example.data.di.RepositoryModule::class,
        DataStoreModule::class,
        ViewModelModule::class,
        ]
)
interface AppComponent : CoreDependencies {
    fun inject(activity: MainActivity)

    fun expenseComponentFactory(): ExpenseComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance @ApplicationContext context: Context
        ): AppComponent
    }
}

