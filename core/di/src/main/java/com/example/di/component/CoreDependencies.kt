package com.example.di.component

import com.example.database.dao.AccountDao
import com.example.database.dao.CategoryDao
import com.example.database.dao.ExpenseDao
import com.example.database.dao.IncomeDao
import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.domain.usecase.account.inter.SyncLocalChangesAccountUseCase
import com.example.domain.usecase.category.inter.GetCategoriesUseCase
import com.example.domain.usecase.expense.inter.SyncLocalChangesExpenseUseCase
import com.example.domain.usecase.income.inter.SyncLocalChangesIncomesUseCase
import com.example.network.api.AccountApi
import com.example.network.api.CategoryApi
import com.example.network.api.TransactionApi


interface CoreDependencies {
    fun transactionApi(): TransactionApi
    fun categoryApi(): CategoryApi
    fun accountApi(): AccountApi
    fun getAccountIdUseCase(): GetAccountIdUseCase
    fun getCategories(): GetCategoriesUseCase
    fun categoryDao(): CategoryDao
    fun accountDao(): AccountDao
    fun expenseDao(): ExpenseDao
    fun incomeDao(): IncomeDao
    fun syncLocalChangesIncomes(): SyncLocalChangesIncomesUseCase
    fun syncLocalChangesExpenses(): SyncLocalChangesExpenseUseCase
    fun syncLocalChangesAccount(): SyncLocalChangesAccountUseCase
}