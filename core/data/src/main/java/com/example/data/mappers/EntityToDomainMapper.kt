package com.example.data.mappers

import com.example.database.entity.account.AccountEntity
import com.example.database.entity.category.CategoryEntity
import com.example.database.entity.expense.ExpenseEntity
import com.example.database.entity.expense.ExpenseFullInfo
import com.example.database.entity.income.IncomeEntity
import com.example.database.entity.income.IncomeFullInfo
import com.example.domain.model.account.Account
import com.example.domain.model.category.Category
import com.example.domain.model.expense.Expense
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.model.income.Income
import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.utils.extensions.string.formatWithSpaces
import com.example.utils.extensions.string.toCurrency

fun AccountEntity.toDomain(): Account = Account(
    id = this.accountId,
    name = this.accountName,
    sum = this.balance.formatWithSpaces(),
    currency = this.currency.toCurrency()
)

fun CategoryEntity.toDomain(): Category = Category(
    id = this.categoryId,
    leadIcon = this.emoji,
    title = this.categoryName,
    isIncome = this.isIncome
)

fun ExpenseFullInfo.toDomain(): Expense = Expense(
    localId = this.expense.localId,
    serverId = this.expense.serverId ?: 0,
    leadIcon = this.category.emoji,
    title = this.category.categoryName,
    amount = this.expense.amount,
    currency = this.account.currency.toCurrency(),
    transactionDate = this.expense.transactionDate,
    accountId = this.account.accountId,
    categoryId = this.category.categoryId
)

fun ExpenseFullInfo.toExpenseDetailed(): ExpenseDetailed = ExpenseDetailed(
    localId = this.expense.localId,
    serverId = this.expense.serverId ?: 0,
    accountId = this.account.accountId,
    accountName = this.account.accountName,
    categoryId = this.category.categoryId,
    categoryName = this.category.categoryName,
    sum = this.expense.amount,
    transactionDate = this.expense.transactionDate,
    comment = this.expense.comment,
    currency = this.account.currency.toCurrency()
)

fun ExpenseEntity.toExpenseUpdate(): ExpenseUpdate = ExpenseUpdate(
    accountId = this.accountId,
    localId = this.localId,
    categoryId = this.categoryId,
    amount = this.amount,
    transactionDate = this.transactionDate,
    comment = this.comment
)

fun IncomeEntity.toIncomeUpdate(): IncomeUpdate = IncomeUpdate(
    accountId = this.accountId,
    localId = this.localId,
    categoryId = this.categoryId,
    amount = this.amount,
    transactionDate = this.transactionDate,
    comment = this.comment
)

fun IncomeFullInfo.toDomain(): Income = Income(
    localId = this.income.localId,
    serverId = this.income.serverId ?: 0,
    leadIcon = this.category.emoji,
    title = this.category.categoryName,
    amount = this.income.amount,
    currency = this.account.currency.toCurrency(),
    transactionDate = this.income.transactionDate,
    accountId = this.account.accountId,
    categoryId = this.category.categoryId
)

fun IncomeFullInfo.toIncomeDetailed(): IncomeDetailed = IncomeDetailed(
    localId = this.income.localId,
    serverId = this.income.serverId ?: 0,
    accountId = this.account.accountId,
    accountName = this.account.accountName,
    categoryId = this.category.categoryId,
    categoryName = this.category.categoryName,
    sum = this.income.amount,
    transactionDate = this.income.transactionDate,
    comment = this.income.comment,
    currency = this.account.currency.toCurrency()
)