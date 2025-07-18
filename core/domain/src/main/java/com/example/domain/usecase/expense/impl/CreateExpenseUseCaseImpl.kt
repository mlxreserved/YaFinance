package com.example.domain.usecase.expense.impl

import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.usecase.expense.inter.CreateExpenseUseCase
import com.example.model.result.Result

class CreateExpenseUseCaseImpl (
    private val expenseRepository: ExpenseRepository
): CreateExpenseUseCase {
    override suspend fun createExpense(expense: ExpenseUpdate): Result<ExpenseUpdate> =
        expenseRepository.createExpense(expense)
}