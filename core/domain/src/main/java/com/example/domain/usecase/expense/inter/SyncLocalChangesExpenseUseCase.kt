package com.example.domain.usecase.expense.inter

interface SyncLocalChangesExpenseUseCase {
    suspend fun syncLocalChanges()
}