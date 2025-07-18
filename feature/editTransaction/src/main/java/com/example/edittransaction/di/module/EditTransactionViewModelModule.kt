package com.example.edittransaction.di.module

import androidx.lifecycle.ViewModel
import com.example.di.module.ViewModelKey
import com.example.edittransaction.internal.ui.EditTransactionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EditTransactionViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EditTransactionViewModel::class)
    fun bindEditTransactionViewModel(editTransactionViewModel: EditTransactionViewModel): ViewModel
}