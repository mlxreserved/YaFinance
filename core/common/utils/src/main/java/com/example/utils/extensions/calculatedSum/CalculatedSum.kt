package com.example.utils.extensions.calculatedSum

fun <T> List<T>.calculatedSumAsString(selector: (T) -> Double): String {
    return this.sumOf(selector).toString()
}