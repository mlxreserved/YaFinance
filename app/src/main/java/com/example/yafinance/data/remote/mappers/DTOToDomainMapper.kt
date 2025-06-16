package com.example.yafinance.data.remote.mappers

import com.example.yafinance.data.remote.models.account.AccountDTO
import com.example.yafinance.data.remote.models.category.CategoryDTO
import com.example.yafinance.data.remote.utils.stringToCurrency
import com.example.yafinance.data.remote.utils.formatAmountWithSpaces
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.models.category.Category

fun AccountDTO.toDomain() = Account(
    id = this.id,
    name = this.name,
    sum = this.balance.formatAmountWithSpaces(),
    currency = this.currency.stringToCurrency()
)

fun CategoryDTO.toDomain() = Category(
    id = this.id,
    leadIcon = this.emoji,
    title = this.name,
    isIncome = this.isIncome
)