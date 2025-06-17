package com.example.yafinance.ui.utils

import java.text.SimpleDateFormat
import java.util.Date

fun String.formatWithSpaces(): String {
    val parts = this.split(".")
    val integerPart = parts[0].replace(Regex("(\\d)(?=(\\d{3})+$)"), "$1 ")
    return if (parts.size > 1) {
        val decimalPart = parts[1].padEnd(2, '0').take(2)
        if (decimalPart == "00") integerPart else "$integerPart,$decimalPart"
    } else {
        integerPart
    }
}

fun String.formatWithoutSpaces(): String {
    return this
        .replace(" ", "")  // Удаляем все пробелы
        .replace(",", ".")  // Заменяем запятую на точку для десятичной части
}

fun String.currencyToString(): String {
    return when(this) {
        "₽" -> "RUB"
        "$" -> "USD"
        "€" -> "EUR"
        else -> ""
    }
}

fun Date.toStartDateString(): String {
    val sdf = SimpleDateFormat("d MMMM yyyy")

    return sdf.format(this)
}

fun Date.toEndDateString(): String {
    val sdf = SimpleDateFormat("hh:mm")

    return sdf.format(this)
}