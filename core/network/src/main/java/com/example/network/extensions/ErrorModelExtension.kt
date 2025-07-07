package com.example.network.extensions

import com.example.model.errorModel.ErrorModel
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException


fun Exception.toErrorModel(): ErrorModel = when (this) {
    is UnknownHostException, is IOException -> ErrorModel.NoInternet
    is HttpException -> when (code()) {
        429 -> ErrorModel.TooManyRequests
        500 -> ErrorModel.InternalServerError
        400 -> ErrorModel.ClientError(code(), "Bad request")
        401 -> ErrorModel.Unauthorized
        else -> ErrorModel.Unknown
    }

    else -> ErrorModel.Unknown
}

