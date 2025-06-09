package com.example.yafinance.ui.utils.mock

import com.example.yafinance.domain.models.category.Category

object CategoriesMock {
    val categories = listOf<Category>(
        Category(
            id = 1,
            title = "Аренда квартиры",
            leadIcon = "\uD83C\uDFE0"
        ),
        Category(
            id = 2,
            title = "Одежда",
            leadIcon = "\uD83D\uDC57"
        ),
        Category(
            id = 3,
            title = "Собачка",
            leadIcon = "\uD83D\uDC36"
        )
    )
}