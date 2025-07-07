package com.example.expense.internal.di.module

import androidx.lifecycle.ViewModel
import com.example.di.module.ViewModelKey
import com.example.expense.internal.ui.ExpensesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ExpenseViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    fun bindExpensesViewModel(expensesViewModel: ExpensesViewModel): ViewModel
}