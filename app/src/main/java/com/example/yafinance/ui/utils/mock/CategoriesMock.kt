package com.example.yafinance.ui.utils.mock

import com.example.yafinance.domain.models.category.Category

object CategoriesMock {
    val categories: List<Category> = listOf(
        Category(
            id = 1,
            leadIcon = "\uD83C\uDFE0",
            title = "Аренда квартиры"
        ),
        Category(
            id = 2,
            leadIcon = "\uD83D\uDC57",
            title = "Одежда"
        ),
        Category(
            id = 3,
            leadIcon = "\uD83D\uDC36",
            title = "На собачку"
        ),
        Category(
            id = 4,
            leadIcon = "\uD83D\uDC36",
            title = "На собачку"
        ),
        Category(
            id = 5,
            leadIcon = "РК",
            title = "Ремонт квартиры"
        ),
        Category(
            id = 6,
            leadIcon = "\uD83C\uDF6D",
            title = "Продукты"
        ),
        Category(
            id = 7,
            leadIcon = "\uD83C\uDFCB\uFE0F\u200D♀\uFE0F",
            title = "Спортзал"
        ),
        Category(
            id = 8,
            leadIcon = "\uD83D\uDC8A",
            title = "Медицина"
        )
    )
}