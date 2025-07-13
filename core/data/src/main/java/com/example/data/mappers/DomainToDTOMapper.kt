package com.example.data.mappers

import com.example.domain.model.account.Account
import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.model.income.IncomeUpdate
import com.example.network.dto.account.AccountRequestDTO
import com.example.network.dto.transaction.request.TransactionRequestDTO


fun Account.toAccountRequestDTO(): AccountRequestDTO =
    AccountRequestDTO(
        name = this.name,
        balance = this.sum,
        currency = this.currency
    )

fun ExpenseUpdate.toTransactionRequestDTO(): TransactionRequestDTO =
    TransactionRequestDTO(
        accountId = this.accountId,
        categoryId = this.categoryId,
        amount = this.amount,
        transactionDate = this.transactionDate,
        comment = this.comment
    )

fun IncomeUpdate.toTransactionRequestDTO(): TransactionRequestDTO =
    TransactionRequestDTO(
        accountId = this.accountId,
        categoryId = this.categoryId,
        amount = this.amount,
        transactionDate = this.transactionDate,
        comment = this.comment
    )