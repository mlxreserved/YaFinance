package com.example.data.mappers

import com.example.domain.model.account.Account
import com.example.domain.model.category.Category
import com.example.domain.model.expense.Expense
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.model.income.Income
import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.network.dto.account.AccountDTO
import com.example.network.dto.category.CategoryDTO
import com.example.network.dto.transaction.TransactionDTO
import com.example.network.dto.transaction.response.TransactionResponseDTO
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

fun TransactionResponseDTO.toExpenseDomain() = Expense(
    id = this.id,
    leadIcon = this.category.emoji,
    title = this.category.name,
    subtitle = this.comment,
    amount = this.amount,
    currency = this.account.currency.toCurrency(),
    transactionDate = this.transactionDate
)

fun TransactionResponseDTO.toIncomeDomain() = Income(
    id = this.id,
    title = this.category.name,
    subtitle = this.comment,
    amount = this.amount,
    currency = this.account.currency.toCurrency(),
    transactionDate = this.transactionDate,
    leadIcon = this.category.emoji
)

fun TransactionResponseDTO.toExpenseDetailed() = ExpenseDetailed(
    id = this.id,
    accountId = this.account.id,
    accountName = this.account.name,
    categoryId = this.category.id,
    categoryName = this.category.name,
    sum = this.amount,
    transactionDate = this.transactionDate,
    currency = this.account.currency.toCurrency(),
    comment = this.comment
)

fun TransactionResponseDTO.toIncomeDetailed() = IncomeDetailed(
    id = this.id,
    accountId = this.account.id,
    accountName = this.account.name,
    categoryId = this.category.id,
    categoryName = this.category.name,
    sum = this.amount,
    transactionDate = this.transactionDate,
    currency = this.account.currency.toCurrency(),
    comment = this.comment
)

fun TransactionDTO.toIncomeUpdate() = IncomeUpdate(
    accountId = this.accountId,
    categoryId = this.categoryId,
    amount = this.amount,
    transactionDate = this.transactionDate,
    comment = this.comment
)

fun TransactionDTO.toExpenseUpdate() = ExpenseUpdate(
    accountId = this.accountId,
    categoryId = this.categoryId,
    amount = this.amount,
    transactionDate = this.transactionDate,
    comment = this.comment
)