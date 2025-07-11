package com.example.yafinance.data.remote.utils


fun String.toCurrency(): String {
    return when (this) {
        "RUB" -> "₽"
        "USD" -> "$"
        "EUR" -> "€"
        else -> ""
    }
}

fun String.formatAmountWithSpaces(): String {
    val parts = this.split(".")
    val integerPart = parts[0].replace(Regex("(\\d)(?=(\\d{3})+$)"), "$1 ")
    return if (parts.size > 1) {
        val decimalPart = parts[1].padEnd(2, '0').take(2)
        if (decimalPart == "00") integerPart else "$integerPart,$decimalPart"
    } else {
        integerPart
    }
}