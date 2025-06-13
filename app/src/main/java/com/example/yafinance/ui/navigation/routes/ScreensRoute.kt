package com.example.yafinance.ui.navigation.routes

import kotlinx.serialization.Serializable

sealed interface ScreensRoute {
    @Serializable
    object SplashRoute: ScreensRoute

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
}