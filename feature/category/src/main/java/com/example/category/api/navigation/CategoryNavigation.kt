package com.example.category.api.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.category.internal.ui.CategoriesScreen
import kotlinx.serialization.Serializable

@Serializable
object CategoryRoute

fun NavGraphBuilder.categoryScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
) {
    composable<CategoryRoute> {
        CategoriesScreen(
            isConnected = isConnected,
            viewModelFactory = viewModelFactory
        )
    }
}