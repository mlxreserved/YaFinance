package com.example.category.di.module

import com.example.category.di.scope.CategoryScope
import com.example.domain.repository.category.CategoryRepository
import com.example.domain.usecase.category.impl.GetCategoriesUseCaseImpl
import com.example.domain.usecase.category.inter.GetCategoriesUseCase
import dagger.Module
import dagger.Provides

@Module
object CategoryUseCaseModule {
    @CategoryScope
    @Provides
    fun provideGetCategoriesUseCase(
        categoryRepository: CategoryRepository
    ): GetCategoriesUseCase =
        GetCategoriesUseCaseImpl(categoryRepository)
}