package com.example.yafinance.data.remote.mappers

import com.example.yafinance.data.remote.models.account.AccountDTO
import com.example.yafinance.data.remote.utils.currencyFormat
import com.example.yafinance.data.remote.utils.formatAmountWithSpaces
import com.example.yafinance.domain.models.account.Account

fun AccountDTO.toDomain() = Account(
    id = this.id,
    name = this.name,
    sum = this.balance.formatAmountWithSpaces(),
    currency = this.currency.currencyFormat()
)