package com.example.data.mappers

import com.example.domain.model.account.Account
import com.example.network.dto.account.AccountRequestDTO


fun Account.toAccountRequestDTO(): AccountRequestDTO =
    AccountRequestDTO(
        name = this.name,
        balance = this.sum,
        currency = this.currency
    )