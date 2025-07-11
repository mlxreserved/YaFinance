package com.example.account.di.module

import androidx.lifecycle.ViewModel
import com.example.account.internal.ui.account.AccountsViewModel
import com.example.account.internal.ui.editAccount.EditAccountViewModel
import com.example.di.module.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AccountViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AccountsViewModel::class)
    fun bindAccountsViewModel(accountsViewModel: AccountsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditAccountViewModel::class)
    fun bindEditAccountViewModel(editAccountViewModel: EditAccountViewModel): ViewModel
}