package com.example.yafinance.data.remote.utils

import com.example.yafinance.domain.utils.Result
import kotlinx.coroutines.delay

suspend fun <T> safeCallWithRetry(
    maxRetries: Int = 3,
    delayMillis: Long = 2000,
    apiCall: suspend () -> T
): Result<T> {
    var attempt = 0
    while (true) {
        try {
            return Result.Success(apiCall())
        } catch (e: Exception) {
            val error = e.toErrorModel()

            if (error.isRetryable() && attempt < maxRetries) {
                attempt++
                delay(delayMillis)
                continue
            } else {
                return Result.Error(error)
            }
        }
    }
}