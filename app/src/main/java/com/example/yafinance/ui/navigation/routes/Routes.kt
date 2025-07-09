package com.example.yafinance.ui.navigation.routes

import com.example.expense.api.navigation.ExpensesAllRoutes
import com.example.income.api.navigation.IncomesAllRoutes
import com.example.yafinance.R
import com.example.yafinance.ui.navigation.routes.ScreensRoute.AccountsAllRoutes
import com.example.yafinance.ui.navigation.routes.ScreensRoute.CategoriesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.SettingsRoute

object Routes {
    val topLevelRoutes = listOf(
        TopLevelRoute(R.string.expenses, ExpensesAllRoutes, R.drawable.ic_downtrade),
        TopLevelRoute(R.string.incomes, IncomesAllRoutes, R.drawable.ic_uptrade),
        TopLevelRoute(R.string.account, AccountsAllRoutes, R.drawable.ic_account),
        TopLevelRoute(R.string.categories, CategoriesRoute, R.drawable.ic_categories),
        TopLevelRoute(R.string.settings, SettingsRoute, R.drawable.ic_settings)
    )
}