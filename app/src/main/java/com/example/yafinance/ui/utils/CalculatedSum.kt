package com.example.yafinance.ui.utils

fun <T> List<T>.calculatedSumAsString(selector: (T) -> Double): String {
    return this.sumOf(selector).toString()
}