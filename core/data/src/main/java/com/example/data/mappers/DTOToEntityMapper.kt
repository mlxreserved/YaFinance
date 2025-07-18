package com.example.data.mappers

import com.example.database.entity.account.AccountEntity
import com.example.database.entity.category.CategoryEntity
import com.example.database.entity.expense.ExpenseEntity
import com.example.database.entity.expense.ExpenseFullInfo
import com.example.database.entity.income.IncomeEntity
import com.example.database.entity.income.IncomeFullInfo
import com.example.network.dto.transaction.TransactionDTO
import com.example.network.dto.transaction.response.TransactionResponseDTO

fun TransactionResponseDTO.toExpenseEntity(
    isDeleted: Boolean = false,
    isAwaiting: Boolean = false
) = ExpenseEntity(
    serverId = this.id,
    amount = this.amount,
    transactionDate = this.transactionDate,
    accountId = this.account.id,
    categoryId = this.category.id,
    isDeleted = isDeleted,
    isExpenseAwaiting = isAwaiting,
    comment = comment
)

fun TransactionResponseDTO.toExpenseFullInfoEntity(
    isDeleted: Boolean = false,
    isAwaiting: Boolean = false
) = ExpenseFullInfo(
    expense = ExpenseEntity(
        accountId = this.account.id,
        categoryId = this.category.id,
        isDeleted = isDeleted,
        isExpenseAwaiting = isAwaiting,
        amount = this.amount,
        transactionDate = this.transactionDate,
        comment = this.comment
    ),
    account = AccountEntity(
        accountId = this.account.id,
        accountName = this.account.name,
        balance = this.account.balance,
        currency = this.account.currency,
        isAccountAwaiting = false
    ),
    category = CategoryEntity(
        categoryId = this.category.id,
        categoryName = this.category.name,
        emoji = this.category.emoji,
        isIncome = this.category.isIncome
    )
)

fun TransactionDTO.toExpenseEntity(
    isDeleted: Boolean = false,
    isAwaiting: Boolean = false
) = ExpenseEntity(
    accountId = this.accountId,
    categoryId = this.categoryId,
    isDeleted = isDeleted,
    isExpenseAwaiting = isAwaiting,
    amount = this.amount,
    transactionDate = this.transactionDate,
    comment = this.comment,
    serverId = this.id
)

fun TransactionDTO.toIncomeEntity(
    isDeleted: Boolean = false,
    isAwaiting: Boolean = false
) = IncomeEntity(
    accountId = this.accountId,
    categoryId = this.categoryId,
    isDeleted = isDeleted,
    isIncomeAwaiting = isAwaiting,
    amount = this.amount,
    transactionDate = this.transactionDate,
    comment = this.comment,
    serverId = this.id
)

fun TransactionResponseDTO.toIncomeEntity(
    isDeleted: Boolean = false,
    isAwaiting: Boolean = false
) = IncomeEntity(
    serverId = this.id,
    amount = this.amount,
    transactionDate = this.transactionDate,
    accountId = this.account.id,
    categoryId = this.category.id,
    isDeleted = isDeleted,
    isIncomeAwaiting = isAwaiting,
    comment = comment
)

fun TransactionResponseDTO.toIncomeFullInfoEntity(
    isDeleted: Boolean = false,
    isAwaiting: Boolean = false
) = IncomeFullInfo(
    income = IncomeEntity(
        accountId = this.account.id,
        categoryId = this.category.id,
        isDeleted = isDeleted,
        isIncomeAwaiting = isAwaiting,
        amount = this.amount,
        transactionDate = this.transactionDate,
        comment = this.comment
    ),
    account = AccountEntity(
        accountId = this.account.id,
        accountName = this.account.name,
        balance = this.account.balance,
        currency = this.account.currency,
        isAccountAwaiting = false
    ),
    category = CategoryEntity(
        categoryId = this.category.id,
        categoryName = this.category.name,
        emoji = this.category.emoji,
        isIncome = this.category.isIncome
    )
)