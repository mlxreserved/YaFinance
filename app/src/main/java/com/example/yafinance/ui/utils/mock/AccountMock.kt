package com.example.yafinance.ui.utils.mock

import com.example.yafinance.domain.models.account.Account

object AccountMock {
    val account = Account(
        id = 1,
        sum = "1000.00",
        currency = "₽"
    )
}