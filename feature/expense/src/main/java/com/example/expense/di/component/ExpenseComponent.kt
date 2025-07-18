package com.example.expense.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.expense.di.scope.ExpenseScope
import com.example.expense.di.module.ExpenseUseCaseModule
import com.example.expense.di.module.ExpenseViewModelModule
import dagger.Subcomponent

@ExpenseScope
@Subcomponent(
    modules = [
        ExpenseViewModelModule::class,
        ExpenseUseCaseModule::class
    ],
)
interface ExpenseComponent {
    fun viewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ExpenseComponent
    }
}