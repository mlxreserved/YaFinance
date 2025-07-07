package com.example.expense.internal.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.expense.internal.di.scope.ExpenseScope
import com.example.expense.internal.di.module.ExpenseModule
import com.example.expense.internal.di.module.ExpenseViewModelModule
import dagger.Subcomponent

@ExpenseScope
@Subcomponent(
    modules = [
        ExpenseViewModelModule::class,
        ExpenseModule::class
        ],
)
interface ExpenseComponent {
    fun viewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ExpenseComponent
    }
}