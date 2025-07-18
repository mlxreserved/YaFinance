package com.example.income.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.income.di.module.IncomeUseCaseModule
import com.example.income.di.module.IncomeViewModelModule
import com.example.income.di.scope.IncomeScope
import dagger.Subcomponent

@IncomeScope
@Subcomponent(
    modules = [
        IncomeViewModelModule::class,
        IncomeUseCaseModule::class
    ],
)
interface IncomeComponent {
    fun viewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): IncomeComponent
    }
}