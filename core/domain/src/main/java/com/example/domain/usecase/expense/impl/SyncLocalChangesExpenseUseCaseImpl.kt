package com.example.domain.usecase.expense.impl

import com.example.domain.repository.expense.ExpenseRepository
import com.example.domain.usecase.expense.inter.SyncLocalChangesExpenseUseCase

class SyncLocalChangesExpenseUseCaseImpl(
    private val expenseRepository: ExpenseRepository
) : SyncLocalChangesExpenseUseCase {
    override suspend fun syncLocalChanges() =
        expenseRepository.syncLocalChanges()
}