package com.example.yafinance.ui.utils

import android.content.Context
import com.example.yafinance.R
import com.example.yafinance.domain.utils.ErrorModel


fun ErrorModel.toUserMessage(context: Context): String = when (this) {
    is ErrorModel.NoInternet -> context.getString(R.string.error_internet)
    is ErrorModel.Unknown -> context.getString(R.string.error_unknown)

    is ErrorModel.Unauthorized -> context.getString(R.string.error_unauthorized)
    is ErrorModel.TooManyRequests -> context.getString(R.string.error_too_many_requests)
    is ErrorModel.InternalServerError -> context.getString(R.string.error_server)

    is ErrorModel.ClientError -> message ?: context.getString(R.string.error_client)
}
