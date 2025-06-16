package com.example.yafinance.ui.navigation.routes

import kotlinx.serialization.Serializable

sealed interface ScreensRoute {

    @Serializable
    object ExpensesAllRoutes: ScreensRoute

    @Serializable
    object AccountsAllRoutes: ScreensRoute

    @Serializable
    object ExpensesRoute : ScreensRoute

    @Serializable
    object IncomesRoute : ScreensRoute

    @Serializable
    object AccountsRoute : ScreensRoute

    @Serializable
    object CategoriesRoute : ScreensRoute

    @Serializable
    object SettingsRoute : ScreensRoute

    @Serializable
    data class EditAccountRoute(
        val id: Int,
        val name: String,
        val amount: String,
        val currency: String
    ) : ScreensRoute

    @Serializable
    object HistoryRoute : ScreensRoute
}