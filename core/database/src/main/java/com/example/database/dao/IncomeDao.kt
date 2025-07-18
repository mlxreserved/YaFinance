package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.income.IncomeEntity
import com.example.database.entity.income.IncomeFullInfo

@Dao
interface IncomeDao {
    @Query(
        """SELECT *
        FROM incomes 
        JOIN accounts ON incomes.account_id = accounts.accountId
        JOIN categories ON incomes.category_id = categories.categoryId
        WHERE (incomes.is_deleted = 0 
        AND incomes.transaction_date > :startDate
        AND incomes.transaction_date < :endDate)
        """
    )
    suspend fun getNotDeletedIncomes(startDate: String?, endDate: String?): List<IncomeFullInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncome(income: IncomeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllIncomes(income: List<IncomeEntity>): List<Long>

    @Query("UPDATE incomes SET is_awaiting_dispatch_income = 1, account_id = :accountId, category_id = :categoryId, transaction_date = :transactionDate, server_id = :serverId, amount = :amount, comment = :comment WHERE localId = :localId")
    suspend fun updateIncomeByLocalId(
        localId: Int,
        serverId: Int?,
        amount: String,
        comment: String?,
        transactionDate: String,
        accountId: Int,
        categoryId: Int
    ): Int

    @Query("UPDATE incomes SET is_awaiting_dispatch_income = 0, server_id = :serverId WHERE localId = :localId")
    suspend fun updateIncomeAwaitingStatus(localId: Int, serverId: Int?): Int

    @Query("UPDATE incomes SET is_deleted = 1, is_awaiting_dispatch_income = IIF(:serverId IS NULL, 0, 1) WHERE localId = :localId")
    suspend fun deleteIncome(localId: Int, serverId: Int?): Int

    @Query(
        """SELECT * 
        FROM incomes
        JOIN accounts ON incomes.account_id = accounts.accountId
        JOIN categories ON incomes.category_id = categories.categoryId
        WHERE incomes.is_awaiting_dispatch_income = 1"""
    )
    suspend fun getIncomesAwaitingDispatch(): List<IncomeFullInfo>

    @Query(
        """SELECT * 
        FROM incomes
        JOIN accounts ON incomes.account_id = accounts.accountId
        JOIN categories ON incomes.category_id = categories.categoryId
        WHERE localId = :localId LIMIT 1"""
    )
    suspend fun getIncomeById(localId: Int): IncomeFullInfo
}
