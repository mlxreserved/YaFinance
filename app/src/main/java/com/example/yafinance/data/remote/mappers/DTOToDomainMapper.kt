package com.example.yafinance.data.remote.mappers

import com.example.yafinance.data.remote.models.account.AccountDTO
import com.example.yafinance.data.remote.models.category.CategoryDTO
import com.example.yafinance.data.remote.models.transaction.response.TransactionResponseDTO
import com.example.yafinance.data.remote.utils.stringToCurrency
import com.example.yafinance.data.remote.utils.formatAmountWithSpaces
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.models.income.Income

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

fun TransactionResponseDTO.toExpenseDomain() = Expense(
    id = this.id,
    leadIcon = this.category.emoji,
    title = this.category.name,
    subtitle = this.comment,
    amount = this.amount.formatAmountWithSpaces(),
    currency = this.account.currency.stringToCurrency(),
    transactionDate = this.transactionDate
)

fun TransactionResponseDTO.toIncomeDomain() = Income(
    id = this.id,
    title = this.category.name,
    subtitle = this.comment,
    amount = this.amount.formatAmountWithSpaces(),
    currency = this.account.currency.stringToCurrency(),
    transactionDate = this.transactionDate,
)