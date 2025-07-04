package com.example.yafinance.domain.usecase.expense.inter

import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.utils.Result
import java.util.Date

/** UseCase для получения расходов за период**/
interface GetExpensesUseCase {
    suspend fun getExpenses(startDate: Date? = null, endDate: Date? = null): Result<List<Expense>>
}