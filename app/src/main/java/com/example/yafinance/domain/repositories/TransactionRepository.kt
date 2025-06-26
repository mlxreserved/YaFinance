package com.example.yafinance.domain.repositories

import com.example.yafinance.domain.utils.Result
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.models.income.Income
import java.util.Date

/** Репозиторий для работы с транзакциями **/
interface TransactionRepository {
    /** Получение расходов за определенный период **/
    suspend fun getExpenses(startDate: Date?, endDate: Date?): Result<List<Expense>>

    /** Получение доходов за определенный период **/
    suspend fun getIncomes(startDate: Date?, endDate: Date?): Result<List<Income>>
}