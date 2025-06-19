package com.example.yafinance.data.remote.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object DateFormatter {
    @SuppressLint("SimpleDateFormat")
    fun Date.dateToString(): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd")

        return sdf.format(this)
    }
}