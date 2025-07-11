package com.example.account.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.account.di.module.AccountUseCaseModule
import com.example.account.di.module.AccountViewModelModule
import com.example.account.di.scope.AccountScope
import dagger.Subcomponent

@AccountScope
@Subcomponent(
    modules = [
        AccountViewModelModule::class,
        AccountUseCaseModule::class
    ],
)
interface AccountComponent {
    fun viewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): AccountComponent
    }
}