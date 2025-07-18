package com.example.edittransaction.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.edittransaction.di.module.EditTransactionUseCaseModule
import com.example.edittransaction.di.module.EditTransactionViewModelModule
import com.example.edittransaction.di.scope.EditTransactionScope
import dagger.Subcomponent

@EditTransactionScope
@Subcomponent(
    modules = [
        EditTransactionViewModelModule::class,
        EditTransactionUseCaseModule::class
    ],
)
interface EditTransactionComponent {
    fun viewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): EditTransactionComponent
    }
}