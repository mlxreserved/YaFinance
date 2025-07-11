package com.example.category.di.module

import androidx.lifecycle.ViewModel
import com.example.category.internal.ui.CategoriesViewModel
import com.example.di.module.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CategoryViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    fun bindCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel
}