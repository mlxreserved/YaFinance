package com.example.yafinance.di

import android.content.Context
import com.example.account.di.component.AccountComponent
import com.example.category.di.component.CategoryComponent
import com.example.data.di.RepositoryModule
import com.example.database.di.DatabaseModule
import com.example.di.component.CoreDependencies
import com.example.di.module.UseCaseModule
import com.example.di.module.ViewModelModule
import com.example.edittransaction.di.component.EditTransactionComponent
import com.example.expense.di.component.ExpenseComponent
import com.example.income.di.component.IncomeComponent
import com.example.network.di.NetworkModule
import com.example.utils.qualifiers.ApplicationContext
import com.example.workmanager.di.WorkerModule
import com.example.yafinance.FinanceApplication
import com.example.yafinance.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
        WorkerModule::class
    ]
)
interface AppComponent : CoreDependencies {
    fun inject(activity: MainActivity)
    fun inject(app: FinanceApplication)

    fun expenseComponentFactory(): ExpenseComponent.Factory
    fun incomeComponentFactory(): IncomeComponent.Factory
    fun accountComponentFactory(): AccountComponent.Factory
    fun categoryComponentFactory(): CategoryComponent.Factory
    fun editTransactionComponentFactory(): EditTransactionComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance @ApplicationContext context: Context
        ): AppComponent
    }
}

