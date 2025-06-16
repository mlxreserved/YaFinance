package com.example.yafinance.data.remote.mappers

import com.example.yafinance.data.remote.models.account.AccountRequestDTO
import com.example.yafinance.domain.models.account.Account

fun Account.toAccountRequestDTO(): AccountRequestDTO =
    AccountRequestDTO(
        name = this.name,
        balance = this.sum,
        currency = this.currency
    )