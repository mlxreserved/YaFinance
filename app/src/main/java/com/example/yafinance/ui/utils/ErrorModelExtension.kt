package com.example.yafinance.ui.utils

import com.example.yafinance.domain.utils.ErrorModel

fun ErrorModel.toUserMessage(): String = when (this) {
    is ErrorModel.NoInternet -> "Нет подключения к интернету"
    is ErrorModel.Unknown -> "Произошла неизвестная ошибка"

    is ErrorModel.Unauthorized -> "Неавторизованный доступ. Выполните вход"
    is ErrorModel.TooManyRequests -> "Слишком много запросов. Попробуйте позже"
    is ErrorModel.InternalServerError -> "Ошибка сервера. Попробуйте позже"

    is ErrorModel.ClientError -> message ?: "Ошибка сервера"
}
