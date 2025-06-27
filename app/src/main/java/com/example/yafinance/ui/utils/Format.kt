package com.example.yafinance.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        .replace(" ", "")
        .replace(",", ".")
}

fun String.currencyToString(): String {
    return when (this) {
        "₽" -> "RUB"
        "$" -> "USD"
        "€" -> "EUR"
        else -> ""
    }
}

fun Date.toDateString(): String {
    val sdf = SimpleDateFormat("d MMMM yyyy")

    return sdf.format(this)
}

fun String.toDateWithTimeString(): String {
    val format = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        Locale.getDefault()
    )

    val formatedStringToDate = format.parse(this)!!

    val sdf = SimpleDateFormat("d MMMM HH:mm")

    return sdf.format(formatedStringToDate)
}