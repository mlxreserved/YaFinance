package com.example.ui.extensions

import android.content.Context
import com.example.model.errorModel.ErrorModel
import com.example.ui.R

fun ErrorModel.toUserMessage(context: Context): String = when (this) {
    is ErrorModel.NoInternet -> context.getString(R.string.error_internet)
    is ErrorModel.Unknown -> context.getString(R.string.error_unknown)

    is ErrorModel.Unauthorized -> context.getString(R.string.error_unauthorized)
    is ErrorModel.TooManyRequests -> context.getString(R.string.error_too_many_requests)
    is ErrorModel.InternalServerError -> context.getString(R.string.error_server)

    is ErrorModel.ClientError -> message ?: context.getString(R.string.error_client)
}
