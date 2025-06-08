package com.example.yafinance.ui.utils

import com.example.yafinance.R
import com.example.yafinance.ui.navigation.routes.Routes.AccountsRoute
import com.example.yafinance.ui.navigation.routes.Routes.CategoriesRoute
import com.example.yafinance.ui.navigation.routes.Routes.ExpensesRoute
import com.example.yafinance.ui.navigation.routes.Routes.IncomesRoute
import com.example.yafinance.ui.navigation.routes.Routes.SettingsRoute

object Routes {
    val topLevelRoutes = listOf(
        TopLevelRoute(R.string.expenses, ExpensesRoute, R.drawable.ic_downtrade),
        TopLevelRoute(R.string.incomes, IncomesRoute, R.drawable.ic_uptrade),
        TopLevelRoute(R.string.account, AccountsRoute, R.drawable.ic_account),
        TopLevelRoute(R.string.categories, CategoriesRoute, R.drawable.ic_categories),
        TopLevelRoute(R.string.settings, SettingsRoute, R.drawable.ic_settings)
    )
}