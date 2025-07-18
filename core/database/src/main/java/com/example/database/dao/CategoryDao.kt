package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.category.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(categories: List<CategoryEntity>): List<Long>

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE is_income = :isIncome")
    suspend fun getCategoriesByType(isIncome: Boolean): List<CategoryEntity>
}