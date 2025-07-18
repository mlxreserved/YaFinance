package com.example.database.entity.income

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.database.entity.account.AccountEntity
import com.example.database.entity.category.CategoryEntity

@Entity(
    tableName = "incomes",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = arrayOf("accountId"),
            childColumns = arrayOf("account_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("category_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true) val localId: Int = 0, // ID дохода локально
    @ColumnInfo(name = "server_id") val serverId: Int? = null, // ID дохода на сервере
    @ColumnInfo(name = "account_id") val accountId: Int,
    @ColumnInfo(name = "category_id")val categoryId: Int,
    @ColumnInfo(name = "is_deleted") val isDeleted: Boolean, // Удалено
    @ColumnInfo(name = "is_awaiting_dispatch_income") val isIncomeAwaiting: Boolean, // Ожидание отправки на сервер
    @ColumnInfo(name = "amount") val amount: String,
    @ColumnInfo(name = "transaction_date") val transactionDate: String,
    @ColumnInfo(name = "comment") val comment: String?
)