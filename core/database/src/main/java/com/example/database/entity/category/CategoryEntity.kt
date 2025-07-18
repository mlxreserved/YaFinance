package com.example.database.entity.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val categoryId: Int,
    @ColumnInfo(name = "category_name") val categoryName: String,
    @ColumnInfo(name = "emoji") val emoji: String,
    @ColumnInfo(name = "is_income") val isIncome: Boolean
)
