package com.example.yafinance.ui.utils.mock

import com.example.yafinance.domain.models.income.Income

object IncomesMock {
    val incomes: List<Income> = listOf(
        Income(
            id = 1,
            title = "Зарплата",
            amount = "500000.00",
            currency = "₽"
        ),
        Income(
            id = 2,
            title = "Подработка",
            amount = "100000.00",
            currency = "₽"
        )
    )
}