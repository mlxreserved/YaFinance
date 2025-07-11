package com.example.yafinance.ui.navigation.routes

import com.example.account.api.navigation.AccountAllRoutes
import com.example.expense.api.navigation.ExpensesAllRoutes
import com.example.income.api.navigation.IncomesAllRoutes
import com.example.yafinance.R
import com.example.category.api.navigation.CategoryRoute

object Routes {
    val topLevelRoutes = listOf(
        TopLevelRoute(R.string.expenses, ExpensesAllRoutes, R.drawable.ic_downtrade),
        TopLevelRoute(R.string.incomes, IncomesAllRoutes, R.drawable.ic_uptrade),
        TopLevelRoute(R.string.account, AccountAllRoutes, R.drawable.ic_account),
        TopLevelRoute(R.string.categories, CategoryRoute, R.drawable.ic_categories),
        TopLevelRoute(R.string.settings, com.example.settings.api.navigation.SettingsRoute, R.drawable.ic_settings)
    )
}