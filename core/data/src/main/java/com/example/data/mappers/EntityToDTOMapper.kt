package com.example.data.mappers

import com.example.database.entity.account.AccountEntity
import com.example.database.entity.expense.ExpenseFullInfo
import com.example.database.entity.income.IncomeFullInfo
import com.example.network.dto.account.AccountRequestDTO
import com.example.network.dto.transaction.request.TransactionRequestDTO
import com.example.utils.extensions.string.formatWithoutSpaces

fun IncomeFullInfo.toTransactionRequest(): TransactionRequestDTO = TransactionRequestDTO(
    accountId = this.account.accountId,
    categoryId = this.category.categoryId,
    amount = this.income.amount,
    transactionDate = this.income.transactionDate,
    comment = this.income.comment
)

fun ExpenseFullInfo.toTransactionRequest(): TransactionRequestDTO = TransactionRequestDTO(
    accountId = this.account.accountId,
    categoryId = this.category.categoryId,
    amount = this.expense.amount,
    transactionDate = this.expense.transactionDate,
    comment = this.expense.comment
)

fun AccountEntity.toTransactionRequest(): AccountRequestDTO = AccountRequestDTO(
    name = this.accountName,
    balance = this.balance.formatWithoutSpaces(),
    currency = this.currency
)