package com.example.yafinance.ui.utils

fun String.isEmoji(): Boolean =
    !this.contains("[A-Za-zА-Яа-я0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())