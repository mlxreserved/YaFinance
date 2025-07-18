package com.example.expense.di.module

import androidx.lifecycle.ViewModel
import com.example.di.module.ViewModelKey
import com.example.expense.internal.ui.expense.ExpensesViewModel
import com.example.expense.internal.ui.expensesAnalyse.ExpenseAnalyseViewModel
import com.example.expense.internal.ui.expensesHistory.ExpensesHistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ExpenseViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    fun bindExpensesViewModel(expensesViewModel: ExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpensesHistoryViewModel::class)
    fun bindExpensesHistoryViewModel(expensesHistoryViewModel: ExpensesHistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpenseAnalyseViewModel::class)
    fun bindExpensesAnalyseViewModel(expenseAnalyseViewModel: ExpenseAnalyseViewModel): ViewModel
}