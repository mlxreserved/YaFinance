package com.example.income.di.module

import androidx.lifecycle.ViewModel
import com.example.di.module.ViewModelKey
import com.example.income.internal.ui.income.IncomesViewModel
import com.example.income.internal.ui.incomesHistory.IncomesHistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface IncomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(IncomesViewModel::class)
    fun bindIncomeViewModel(incomesViewModel: IncomesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomesHistoryViewModel::class)
    fun bindIncomesHistoryViewModel(incomesHistoryViewModel: IncomesHistoryViewModel): ViewModel
}