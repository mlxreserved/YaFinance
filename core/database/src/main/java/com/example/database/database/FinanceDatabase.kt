package com.example.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.AccountDao
import com.example.database.dao.CategoryDao
import com.example.database.dao.ExpenseDao
import com.example.database.dao.IncomeDao
import com.example.database.entity.account.AccountEntity
import com.example.database.entity.category.CategoryEntity
import com.example.database.entity.expense.ExpenseEntity
import com.example.database.entity.income.IncomeEntity

@Database(
    entities = [
        AccountEntity::class,
        ExpenseEntity::class,
        IncomeEntity::class,
        CategoryEntity::class
    ],
    version = 1
)
abstract class FinanceDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun incomeDao(): IncomeDao
    abstract fun categoryDao(): CategoryDao
}