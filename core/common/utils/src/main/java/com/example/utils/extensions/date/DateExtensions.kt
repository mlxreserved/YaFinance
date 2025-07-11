package com.example.utils.extensions.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun Date.dateToString(): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd")

    return sdf.format(this)
}