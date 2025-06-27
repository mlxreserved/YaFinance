package com.example.yafinance.domain.utils


/** Кастомные ошибки для определения проблемы и уведомления пользователя **/
sealed class ErrorModel {
    object NoInternet : ErrorModel()
    object Unknown : ErrorModel()

    object TooManyRequests : ErrorModel()
    object Unauthorized : ErrorModel()
    object InternalServerError : ErrorModel()

    data class ClientError(val code: Int, val message: String?) : ErrorModel()

    /** Проверка на возможность совершения повторных запросов **/
    fun isRetryable(): Boolean = this is TooManyRequests || this is InternalServerError
}



