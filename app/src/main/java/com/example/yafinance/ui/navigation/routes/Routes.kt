package com.example.yafinance.ui.navigation.routes

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    object ExpensesRoute : Routes

    @Serializable
    object IncomesRoute : Routes

    @Serializable
    object AccountsRoute : Routes

    @Serializable
    object CategoriesRoute : Routes

    @Serializable
    object SettingsRoute : Routes
}