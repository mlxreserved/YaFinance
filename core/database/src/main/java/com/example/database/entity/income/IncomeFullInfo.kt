package com.example.database.entity.income

import androidx.room.Embedded
import com.example.database.entity.account.AccountEntity
import com.example.database.entity.category.CategoryEntity

data class IncomeFullInfo(
        @Embedded val income: IncomeEntity,
        @Embedded val account: AccountEntity,
        @Embedded val category: CategoryEntity
    )