package com.example.utils.extensions.string

import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun String.toCurrency(): String {
    return when (this) {
        "RUB" -> "₽"
        "USD" -> "$"
        "EUR" -> "€"
        else -> ""
    }
}

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

fun Date.toTimeString(): String {
    val sdf = SimpleDateFormat("HH:mm")

    return sdf.format(this)
}

fun Date.toDateWithDotsString(): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy")

    return sdf.format(this)
}

fun String.toDateWithTimeString(): String {
    val format = if(this.length == 20) {
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault()
        )
    } else if(this.length == 24) {
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
    } else {
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
            Locale.getDefault()
        )
    }

    val formatedStringToDate = format.parse(this)!!

    val sdf = SimpleDateFormat("d MMMM HH:mm")

    return sdf.format(formatedStringToDate)
}

fun Date.toStringWithZone(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        .withZone(ZoneId.of("UTC"))
    return formatter.format(this.toInstant())
}


fun String.toLongDate(): Date {
    val formatter = if(this.length == 20) {
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault()
        )
    } else if(this.length == 24) {
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
    } else {
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
            Locale.getDefault()
        )
    }

    return formatter.parse(this)
}

fun String.isEmoji(): Boolean =
    !this.contains("[A-Za-zА-Яа-я0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())