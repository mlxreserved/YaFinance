package com.example.database.entity.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey val accountId: Int,
    @ColumnInfo(name = "account_name") val accountName: String,
    @ColumnInfo(name = "balance") val balance: String,
    @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "is_awaiting_dispatch_account") val isAccountAwaiting: Boolean
)
