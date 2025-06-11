package com.example.yafinance.ui.utils.mock

import com.example.yafinance.domain.models.expense.Expense

object ExpensesMock {
    val expenses: List<Expense> = listOf(
        Expense(
            id = 1,
            leadIcon = "\uD83C\uDFE0",
            title = "Аренда квартиры",
            amount = "100000.00",
            currency = "₽"
        ),
        Expense(
            id = 2,
            leadIcon = "\uD83D\uDC57",
            title = "Одежда",
            amount = "100000.00",
            currency = "₽"
        ),
        Expense(
            id = 3,
            leadIcon = "\uD83D\uDC36",
            title = "На собачку",
            subtitle = "Джек",
            amount = "100000.00",
            currency = "₽"
        ),
        Expense(
            id = 4,
            leadIcon = "\uD83D\uDC36",
            title = "На собачку",
            subtitle = "Энни",
            amount = "100000.00",
            currency = "₽"
        ),
        Expense(
            id = 5,
            leadIcon = "РК",
            title = "Ремонт квартиры",
            amount = "100000.00",
            currency = "₽"
        ),
        Expense(
            id = 6,
            leadIcon = "\uD83C\uDF6D",
            title = "Продукты",
            amount = "100000.00",
            currency = "₽"
        ),
        Expense(
            id = 7,
            leadIcon = "\uD83C\uDFCB\uFE0F\u200D♀\uFE0F",
            title = "Спортзал",
            amount = "100000.00",
            currency = "₽"
        ),
        Expense(
            id = 8,
            leadIcon = "\uD83D\uDC8A",
            title = "Медицина",
            amount = "100000.20",
            currency = "₽"
        )
    )
}