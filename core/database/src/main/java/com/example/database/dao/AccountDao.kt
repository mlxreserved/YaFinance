package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.account.AccountEntity

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(accountEntity: AccountEntity): Long

    @Query("SELECT * FROM accounts")
    suspend fun getAccount(): List<AccountEntity>

    @Query("UPDATE accounts SET is_awaiting_dispatch_account = 1, currency = :currency, balance = :balance, account_name = :accountName WHERE accountId = :accountId")
    suspend fun updateAccount(accountId: Int, currency: String, balance: String, accountName: String): Int

    @Query("UPDATE accounts SET is_awaiting_dispatch_account = 0 WHERE accountId = :accountId")
    suspend fun updateAccountAwaitingStatus(accountId: Int): Int
}