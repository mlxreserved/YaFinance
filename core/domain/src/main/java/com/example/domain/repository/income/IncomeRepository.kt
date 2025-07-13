package com.example.domain.repository.income

import com.example.domain.model.income.Income
import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.model.result.Result
import java.util.Date

interface IncomeRepository {
    suspend fun getIncomes(startDate: Date?, endDate: Date?): Result<List<Income>>

    suspend fun getIncomeById(id: Int): Result<IncomeDetailed>

    suspend fun updateIncomeById(id: Int, income: IncomeUpdate): Result<IncomeDetailed>

    suspend fun deleteIncomeById(id: Int)

    suspend fun createIncome(income: IncomeUpdate): Result<IncomeUpdate>
}