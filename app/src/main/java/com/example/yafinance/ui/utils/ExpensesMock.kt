package com.example.yafinance.ui.utils

import com.example.yafinance.domain.models.expense.Expense

object ExpensesMock {
    val expenses = listOf<Expense>(
        Expense(
            id = 1,
            leadIcon = "\uD83D\uDCB0",
            title = "На собачку",
            subtitle = "Джек",
            amount = "1000.00",
            currency = "₽"
        ),
        Expense(
            id = 2,
            leadIcon = "\uD83D\uDCB0",
            title = "На собачку",
            amount = "1000.00",
            currency = "₽"
        ),
        Expense(
            id = 3,
            leadIcon = "\uD83D\uDCB0",
            title = "Зарплата",
            amount = "1000.00",
            currency = "₽"
        ),
    )
}