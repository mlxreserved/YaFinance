package com.example.category.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.category.di.module.CategoryViewModelModule
import com.example.category.di.scope.CategoryScope
import dagger.Subcomponent

@CategoryScope
@Subcomponent(
    modules = [
        CategoryViewModelModule::class
    ],
)
interface CategoryComponent {
    fun viewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): CategoryComponent
    }
}