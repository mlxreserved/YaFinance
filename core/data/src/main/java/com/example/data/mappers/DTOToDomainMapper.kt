package com.example.data.mappers

import com.example.domain.model.account.Account
import com.example.domain.model.category.Category
import com.example.network.dto.account.AccountDTO
import com.example.network.dto.category.CategoryDTO
import com.example.utils.extensions.string.formatWithSpaces
import com.example.utils.extensions.string.toCurrency

fun AccountDTO.toDomain() = Account(
    id = this.id,
    name = this.name,
    sum = this.balance.formatWithSpaces(),
    currency = this.currency.toCurrency()
)

fun CategoryDTO.toDomain() = Category(
    id = this.id,
    leadIcon = this.emoji,
    title = this.name,
    isIncome = this.isIncome
)