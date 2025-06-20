package com.example.yafinance.domain.utils

sealed class Result<out T> {

    data class Success<T>(val result: T) : Result<T>()
    data class Error(val error: ErrorModel) : Result<Nothing>()

}