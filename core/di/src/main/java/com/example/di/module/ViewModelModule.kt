package com.example.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ui.networkMonitor.NetworkStatusViewModel
import com.example.ui.snackbar.SnackbarViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SnackbarViewModel::class)
    fun bindSnackbarViewModel(snackbarViewModel: SnackbarViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NetworkStatusViewModel::class)
    fun bindNetworkMonitorViewModel(networkStatusViewModel: NetworkStatusViewModel): ViewModel
}