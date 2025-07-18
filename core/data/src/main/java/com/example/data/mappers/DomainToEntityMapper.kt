package com.example.data.mappers

import com.example.database.entity.account.AccountEntity
import com.example.database.entity.category.CategoryEntity
import com.example.database.entity.expense.ExpenseEntity
import com.example.database.entity.income.IncomeEntity
import com.example.domain.model.account.Account
import com.example.domain.model.category.Category
import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.model.income.IncomeUpdate
import com.example.utils.extensions.string.currencyToString

fun Account.toEntity(isAwaiting: Boolean = false): AccountEntity = AccountEntity(
    accountId = this.id,
    accountName = this.name,
    currency = this.currency.currencyToString(),
    balance = this.sum,
    isAccountAwaiting = isAwaiting
)

fun Category.toEntity(): CategoryEntity = CategoryEntity(
    categoryId = this.id,
    categoryName = this.title,
    emoji = this.leadIcon,
    isIncome = this.isIncome
)

fun ExpenseUpdate.toEntity(isDeleted: Boolean = false, isAwaiting: Boolean = false) = ExpenseEntity(
    accountId = this.accountId,
    categoryId = this.categoryId,
    isDeleted = isDeleted,
    isExpenseAwaiting = isAwaiting,
    amount = this.amount,
    transactionDate = this.transactionDate,
    comment = this.comment
)

fun IncomeUpdate.toEntity(isDeleted: Boolean = false, isAwaiting: Boolean = false) = IncomeEntity(
    accountId = this.accountId,
    categoryId = this.categoryId,
    isDeleted = isDeleted,
    isIncomeAwaiting = isAwaiting,
    amount = this.amount,
    transactionDate = this.transactionDate,
    comment = this.comment
)