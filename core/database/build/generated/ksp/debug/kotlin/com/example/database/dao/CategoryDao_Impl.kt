package com.example.database.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.database.entity.category.CategoryEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CategoryDao_Impl(
  __db: RoomDatabase,
) : CategoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCategoryEntity: EntityInsertAdapter<CategoryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCategoryEntity = object : EntityInsertAdapter<CategoryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `categories` (`categoryId`,`category_name`,`emoji`,`is_income`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CategoryEntity) {
        statement.bindLong(1, entity.categoryId.toLong())
        statement.bindText(2, entity.categoryName)
        statement.bindText(3, entity.emoji)
        val _tmp: Int = if (entity.isIncome) 1 else 0
        statement.bindLong(4, _tmp.toLong())
      }
    }
  }

  public override suspend fun insertAllCategories(categories: List<CategoryEntity>): List<Long> =
      performSuspending(__db, false, true) { _connection ->
    val _result: List<Long> = __insertAdapterOfCategoryEntity.insertAndReturnIdsList(_connection,
        categories)
    _result
  }

  public override suspend fun getCategories(): List<CategoryEntity> {
    val _sql: String = "SELECT * FROM categories"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfEmoji: Int = getColumnIndexOrThrow(_stmt, "emoji")
        val _columnIndexOfIsIncome: Int = getColumnIndexOrThrow(_stmt, "is_income")
        val _result: MutableList<CategoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoryEntity
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId).toInt()
          val _tmpCategoryName: String
          _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          val _tmpEmoji: String
          _tmpEmoji = _stmt.getText(_columnIndexOfEmoji)
          val _tmpIsIncome: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsIncome).toInt()
          _tmpIsIncome = _tmp != 0
          _item = CategoryEntity(_tmpCategoryId,_tmpCategoryName,_tmpEmoji,_tmpIsIncome)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCategoriesByType(isIncome: Boolean): List<CategoryEntity> {
    val _sql: String = "SELECT * FROM categories WHERE is_income = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (isIncome) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfEmoji: Int = getColumnIndexOrThrow(_stmt, "emoji")
        val _columnIndexOfIsIncome: Int = getColumnIndexOrThrow(_stmt, "is_income")
        val _result: MutableList<CategoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoryEntity
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId).toInt()
          val _tmpCategoryName: String
          _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          val _tmpEmoji: String
          _tmpEmoji = _stmt.getText(_columnIndexOfEmoji)
          val _tmpIsIncome: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsIncome).toInt()
          _tmpIsIncome = _tmp_1 != 0
          _item = CategoryEntity(_tmpCategoryId,_tmpCategoryName,_tmpEmoji,_tmpIsIncome)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
