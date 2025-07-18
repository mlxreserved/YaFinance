package com.example.database.entity.expense

import androidx.room.Embedded
import com.example.database.entity.account.AccountEntity
import com.example.database.entity.category.CategoryEntity

data class ExpenseFullInfo(
        @Embedded val expense: ExpenseEntity,
        @Embedded val account: AccountEntity,
        @Embedded val category: CategoryEntity
    )