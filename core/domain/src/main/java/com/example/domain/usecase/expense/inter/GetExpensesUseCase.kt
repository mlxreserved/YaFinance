package com.example.domain.usecase.expense.inter

import com.example.domain.model.expense.Expense
import com.example.model.result.Result
import java.util.Date

/** UseCase для получения расходов за период**/
interface GetExpensesUseCase {
    suspend fun getExpenses(startDate: Date? = null, endDate: Date? = null): Result<List<Expense>>
}