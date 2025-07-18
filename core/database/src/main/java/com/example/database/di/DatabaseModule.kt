package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.dao.AccountDao
import com.example.database.dao.CategoryDao
import com.example.database.dao.ExpenseDao
import com.example.database.dao.IncomeDao
import com.example.database.database.FinanceDatabase
import com.example.utils.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FinanceDatabase =
        Room.databaseBuilder(
            context = context,
            klass = FinanceDatabase::class.java,
            name = "finance-database"
        ).build()

    @Provides
    @Singleton
    fun provideAccountDao(database: FinanceDatabase): AccountDao =
        database.accountDao()

    @Provides
    @Singleton
    fun provideExpenseDao(database: FinanceDatabase): ExpenseDao =
        database.expenseDao()

    @Provides
    @Singleton
    fun provideIncomeDao(database: FinanceDatabase): IncomeDao =
        database.incomeDao()

    @Provides
    @Singleton
    fun provideCategoryDao(database: FinanceDatabase): CategoryDao =
        database.categoryDao()
}