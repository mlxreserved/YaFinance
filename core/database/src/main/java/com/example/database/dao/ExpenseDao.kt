package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.expense.ExpenseEntity
import com.example.database.entity.expense.ExpenseFullInfo

@Dao
interface ExpenseDao {
    @Query("""SELECT *
        FROM expenses 
        JOIN accounts ON expenses.account_id = accounts.accountId
        JOIN categories ON expenses.category_id = categories.categoryId
        WHERE (expenses.is_deleted = 0 
        AND expenses.transaction_date > :startDate
        AND expenses.transaction_date < :endDate)
        """)
    suspend fun getNotDeletedExpenses(startDate: String?, endDate: String?): List<ExpenseFullInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllExpenses(income: List<ExpenseEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity): Long

    @Query("UPDATE expenses SET is_awaiting_dispatch_expense = 1, account_id = :accountId, category_id = :categoryId, transaction_date = :transactionDate, amount = :amount, server_id = :serverId, comment = :comment WHERE localId = :localId")
    suspend fun updateExpenseByLocalId(
        localId: Int,
        serverId: Int?,
        amount: String,
        comment: String?,
        transactionDate: String,
        accountId: Int,
        categoryId: Int
    ): Int

    @Query("UPDATE expenses SET is_awaiting_dispatch_expense = 0, server_id = :serverId WHERE localId = :localId")
    suspend fun updateExpenseAwaitingStatus(localId: Int, serverId: Int?): Int

    @Query("UPDATE expenses SET is_deleted = 1, is_awaiting_dispatch_expense = IIF(:serverId IS NULL, 0, 1) WHERE localId = :localId")
    suspend fun deleteExpense(localId: Int, serverId: Int?): Int

    @Query("""SELECT *
        FROM expenses
        JOIN accounts ON expenses.account_id = accounts.accountId
        JOIN categories ON expenses.category_id = categories.categoryId
        WHERE (is_awaiting_dispatch_expense = 1 OR server_id IS NULL)""")
    suspend fun getExpensesAwaitingDispatch(): List<ExpenseFullInfo>

    @Query("""SELECT *
        FROM expenses
        JOIN accounts ON expenses.account_id = accounts.accountId
        JOIN categories ON expenses.category_id = categories.categoryId
        WHERE localId = :localId LIMIT 1""")
    suspend fun getExpenseById(localId: Int): ExpenseFullInfo
}
