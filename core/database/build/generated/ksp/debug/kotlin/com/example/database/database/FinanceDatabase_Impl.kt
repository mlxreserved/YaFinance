package com.example.database.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.example.database.dao.AccountDao
import com.example.database.dao.AccountDao_Impl
import com.example.database.dao.CategoryDao
import com.example.database.dao.CategoryDao_Impl
import com.example.database.dao.ExpenseDao
import com.example.database.dao.ExpenseDao_Impl
import com.example.database.dao.IncomeDao
import com.example.database.dao.IncomeDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FinanceDatabase_Impl : FinanceDatabase() {
  private val _accountDao: Lazy<AccountDao> = lazy {
    AccountDao_Impl(this)
  }

  private val _expenseDao: Lazy<ExpenseDao> = lazy {
    ExpenseDao_Impl(this)
  }

  private val _incomeDao: Lazy<IncomeDao> = lazy {
    IncomeDao_Impl(this)
  }

  private val _categoryDao: Lazy<CategoryDao> = lazy {
    CategoryDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "243c68b8a7e98cba7ce71879c4ff5f43", "b697b6661a674f15f95df85b650d2f66") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `accounts` (`accountId` INTEGER NOT NULL, `account_name` TEXT NOT NULL, `balance` TEXT NOT NULL, `currency` TEXT NOT NULL, `is_awaiting_dispatch_account` INTEGER NOT NULL, PRIMARY KEY(`accountId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `server_id` INTEGER, `account_id` INTEGER NOT NULL, `category_id` INTEGER NOT NULL, `is_deleted` INTEGER NOT NULL, `is_awaiting_dispatch_expense` INTEGER NOT NULL, `amount` TEXT NOT NULL, `transaction_date` TEXT NOT NULL, `comment` TEXT, FOREIGN KEY(`account_id`) REFERENCES `accounts`(`accountId`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`category_id`) REFERENCES `categories`(`categoryId`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `incomes` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `server_id` INTEGER, `account_id` INTEGER NOT NULL, `category_id` INTEGER NOT NULL, `is_deleted` INTEGER NOT NULL, `is_awaiting_dispatch_income` INTEGER NOT NULL, `amount` TEXT NOT NULL, `transaction_date` TEXT NOT NULL, `comment` TEXT, FOREIGN KEY(`account_id`) REFERENCES `accounts`(`accountId`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`category_id`) REFERENCES `categories`(`categoryId`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`categoryId` INTEGER NOT NULL, `category_name` TEXT NOT NULL, `emoji` TEXT NOT NULL, `is_income` INTEGER NOT NULL, PRIMARY KEY(`categoryId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '243c68b8a7e98cba7ce71879c4ff5f43')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `accounts`")
        connection.execSQL("DROP TABLE IF EXISTS `expenses`")
        connection.execSQL("DROP TABLE IF EXISTS `incomes`")
        connection.execSQL("DROP TABLE IF EXISTS `categories`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsAccounts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAccounts.put("accountId", TableInfo.Column("accountId", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccounts.put("account_name", TableInfo.Column("account_name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccounts.put("balance", TableInfo.Column("balance", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccounts.put("currency", TableInfo.Column("currency", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccounts.put("is_awaiting_dispatch_account",
            TableInfo.Column("is_awaiting_dispatch_account", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAccounts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAccounts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAccounts: TableInfo = TableInfo("accounts", _columnsAccounts, _foreignKeysAccounts,
            _indicesAccounts)
        val _existingAccounts: TableInfo = read(connection, "accounts")
        if (!_infoAccounts.equals(_existingAccounts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |accounts(com.example.database.entity.account.AccountEntity).
              | Expected:
              |""".trimMargin() + _infoAccounts + """
              |
              | Found:
              |""".trimMargin() + _existingAccounts)
        }
        val _columnsExpenses: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsExpenses.put("localId", TableInfo.Column("localId", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("server_id", TableInfo.Column("server_id", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("account_id", TableInfo.Column("account_id", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("category_id", TableInfo.Column("category_id", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("is_deleted", TableInfo.Column("is_deleted", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("is_awaiting_dispatch_expense",
            TableInfo.Column("is_awaiting_dispatch_expense", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("amount", TableInfo.Column("amount", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("transaction_date", TableInfo.Column("transaction_date", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("comment", TableInfo.Column("comment", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysExpenses: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysExpenses.add(TableInfo.ForeignKey("accounts", "CASCADE", "NO ACTION",
            listOf("account_id"), listOf("accountId")))
        _foreignKeysExpenses.add(TableInfo.ForeignKey("categories", "CASCADE", "NO ACTION",
            listOf("category_id"), listOf("categoryId")))
        val _indicesExpenses: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoExpenses: TableInfo = TableInfo("expenses", _columnsExpenses, _foreignKeysExpenses,
            _indicesExpenses)
        val _existingExpenses: TableInfo = read(connection, "expenses")
        if (!_infoExpenses.equals(_existingExpenses)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |expenses(com.example.database.entity.expense.ExpenseEntity).
              | Expected:
              |""".trimMargin() + _infoExpenses + """
              |
              | Found:
              |""".trimMargin() + _existingExpenses)
        }
        val _columnsIncomes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsIncomes.put("localId", TableInfo.Column("localId", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsIncomes.put("server_id", TableInfo.Column("server_id", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsIncomes.put("account_id", TableInfo.Column("account_id", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsIncomes.put("category_id", TableInfo.Column("category_id", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsIncomes.put("is_deleted", TableInfo.Column("is_deleted", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsIncomes.put("is_awaiting_dispatch_income",
            TableInfo.Column("is_awaiting_dispatch_income", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsIncomes.put("amount", TableInfo.Column("amount", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsIncomes.put("transaction_date", TableInfo.Column("transaction_date", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsIncomes.put("comment", TableInfo.Column("comment", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysIncomes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysIncomes.add(TableInfo.ForeignKey("accounts", "CASCADE", "NO ACTION",
            listOf("account_id"), listOf("accountId")))
        _foreignKeysIncomes.add(TableInfo.ForeignKey("categories", "CASCADE", "NO ACTION",
            listOf("category_id"), listOf("categoryId")))
        val _indicesIncomes: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoIncomes: TableInfo = TableInfo("incomes", _columnsIncomes, _foreignKeysIncomes,
            _indicesIncomes)
        val _existingIncomes: TableInfo = read(connection, "incomes")
        if (!_infoIncomes.equals(_existingIncomes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |incomes(com.example.database.entity.income.IncomeEntity).
              | Expected:
              |""".trimMargin() + _infoIncomes + """
              |
              | Found:
              |""".trimMargin() + _existingIncomes)
        }
        val _columnsCategories: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCategories.put("categoryId", TableInfo.Column("categoryId", "INTEGER", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("category_name", TableInfo.Column("category_name", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("emoji", TableInfo.Column("emoji", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("is_income", TableInfo.Column("is_income", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCategories: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCategories: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCategories: TableInfo = TableInfo("categories", _columnsCategories,
            _foreignKeysCategories, _indicesCategories)
        val _existingCategories: TableInfo = read(connection, "categories")
        if (!_infoCategories.equals(_existingCategories)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |categories(com.example.database.entity.category.CategoryEntity).
              | Expected:
              |""".trimMargin() + _infoCategories + """
              |
              | Found:
              |""".trimMargin() + _existingCategories)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "accounts", "expenses",
        "incomes", "categories")
  }

  public override fun clearAllTables() {
    super.performClear(true, "accounts", "expenses", "incomes", "categories")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(AccountDao::class, AccountDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ExpenseDao::class, ExpenseDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(IncomeDao::class, IncomeDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CategoryDao::class, CategoryDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun accountDao(): AccountDao = _accountDao.value

  public override fun expenseDao(): ExpenseDao = _expenseDao.value

  public override fun incomeDao(): IncomeDao = _incomeDao.value

  public override fun categoryDao(): CategoryDao = _categoryDao.value
}
