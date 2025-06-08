package com.example.yafinance.ui.utils

object Format {

    fun formatWithSpaces(number: String): String {
        val parts = number.split(".")
        val integerPart = parts[0].replace(Regex("(\\d)(?=(\\d{3})+$)"), "$1 ")
        return "$integerPart,${parts[1]}"
    }

}