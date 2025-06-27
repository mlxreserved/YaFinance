package com.example.yafinance.domain.repositories

import com.example.yafinance.domain.utils.Result
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.models.income.Income
import java.util.Date

interface TransactionRepository {
    suspend fun getExpenses(startDate: Date?, endDate: Date?): Result<List<Expense>>

    suspend fun getIncomes(startDate: Date?, endDate: Date?): Result<List<Income>>
}