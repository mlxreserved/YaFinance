package com.example.database.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.database.entity.account.AccountEntity
import com.example.database.entity.category.CategoryEntity
import com.example.database.entity.expense.ExpenseEntity
import com.example.database.entity.expense.ExpenseFullInfo
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
public class ExpenseDao_Impl(
  __db: RoomDatabase,
) : ExpenseDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfExpenseEntity: EntityInsertAdapter<ExpenseEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfExpenseEntity = object : EntityInsertAdapter<ExpenseEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `expenses` (`localId`,`server_id`,`account_id`,`category_id`,`is_deleted`,`is_awaiting_dispatch_expense`,`amount`,`transaction_date`,`comment`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ExpenseEntity) {
        statement.bindLong(1, entity.localId.toLong())
        val _tmpServerId: Int? = entity.serverId
        if (_tmpServerId == null) {
          statement.bindNull(2)
        } else {
          statement.bindLong(2, _tmpServerId.toLong())
        }
        statement.bindLong(3, entity.accountId.toLong())
        statement.bindLong(4, entity.categoryId.toLong())
        val _tmp: Int = if (entity.isDeleted) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isExpenseAwaiting) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        statement.bindText(7, entity.amount)
        statement.bindText(8, entity.transactionDate)
        val _tmpComment: String? = entity.comment
        if (_tmpComment == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpComment)
        }
      }
    }
  }

  public override suspend fun insertAllExpenses(income: List<ExpenseEntity>): List<Long> =
      performSuspending(__db, false, true) { _connection ->
    val _result: List<Long> = __insertAdapterOfExpenseEntity.insertAndReturnIdsList(_connection,
        income)
    _result
  }

  public override suspend fun insertExpense(expense: ExpenseEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfExpenseEntity.insertAndReturnId(_connection, expense)
    _result
  }

  public override suspend fun getNotDeletedExpenses(startDate: String?, endDate: String?):
      List<ExpenseFullInfo> {
    val _sql: String = """
        |SELECT *
        |        FROM expenses 
        |        JOIN accounts ON expenses.account_id = accounts.accountId
        |        JOIN categories ON expenses.category_id = categories.categoryId
        |        WHERE (expenses.is_deleted = 0 
        |        AND expenses.transaction_date > ?
        |        AND expenses.transaction_date < ?)
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (startDate == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, startDate)
        }
        _argIndex = 2
        if (endDate == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, endDate)
        }
        val _columnIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _columnIndexOfServerId: Int = getColumnIndexOrThrow(_stmt, "server_id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "account_id")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "is_deleted")
        val _columnIndexOfIsExpenseAwaiting: Int = getColumnIndexOrThrow(_stmt,
            "is_awaiting_dispatch_expense")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfTransactionDate: Int = getColumnIndexOrThrow(_stmt, "transaction_date")
        val _columnIndexOfComment: Int = getColumnIndexOrThrow(_stmt, "comment")
        val _columnIndexOfAccountId_1: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfAccountName: Int = getColumnIndexOrThrow(_stmt, "account_name")
        val _columnIndexOfBalance: Int = getColumnIndexOrThrow(_stmt, "balance")
        val _columnIndexOfCurrency: Int = getColumnIndexOrThrow(_stmt, "currency")
        val _columnIndexOfIsAccountAwaiting: Int = getColumnIndexOrThrow(_stmt,
            "is_awaiting_dispatch_account")
        val _columnIndexOfCategoryId_1: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfEmoji: Int = getColumnIndexOrThrow(_stmt, "emoji")
        val _columnIndexOfIsIncome: Int = getColumnIndexOrThrow(_stmt, "is_income")
        val _result: MutableList<ExpenseFullInfo> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExpenseFullInfo
          val _tmpExpense: ExpenseEntity
          val _tmpLocalId: Int
          _tmpLocalId = _stmt.getLong(_columnIndexOfLocalId).toInt()
          val _tmpServerId: Int?
          if (_stmt.isNull(_columnIndexOfServerId)) {
            _tmpServerId = null
          } else {
            _tmpServerId = _stmt.getLong(_columnIndexOfServerId).toInt()
          }
          val _tmpAccountId: Int
          _tmpAccountId = _stmt.getLong(_columnIndexOfAccountId).toInt()
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId).toInt()
          val _tmpIsDeleted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp != 0
          val _tmpIsExpenseAwaiting: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsExpenseAwaiting).toInt()
          _tmpIsExpenseAwaiting = _tmp_1 != 0
          val _tmpAmount: String
          _tmpAmount = _stmt.getText(_columnIndexOfAmount)
          val _tmpTransactionDate: String
          _tmpTransactionDate = _stmt.getText(_columnIndexOfTransactionDate)
          val _tmpComment: String?
          if (_stmt.isNull(_columnIndexOfComment)) {
            _tmpComment = null
          } else {
            _tmpComment = _stmt.getText(_columnIndexOfComment)
          }
          _tmpExpense =
              ExpenseEntity(_tmpLocalId,_tmpServerId,_tmpAccountId,_tmpCategoryId,_tmpIsDeleted,_tmpIsExpenseAwaiting,_tmpAmount,_tmpTransactionDate,_tmpComment)
          val _tmpAccount: AccountEntity
          val _tmpAccountId_1: Int
          _tmpAccountId_1 = _stmt.getLong(_columnIndexOfAccountId_1).toInt()
          val _tmpAccountName: String
          _tmpAccountName = _stmt.getText(_columnIndexOfAccountName)
          val _tmpBalance: String
          _tmpBalance = _stmt.getText(_columnIndexOfBalance)
          val _tmpCurrency: String
          _tmpCurrency = _stmt.getText(_columnIndexOfCurrency)
          val _tmpIsAccountAwaiting: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsAccountAwaiting).toInt()
          _tmpIsAccountAwaiting = _tmp_2 != 0
          _tmpAccount =
              AccountEntity(_tmpAccountId_1,_tmpAccountName,_tmpBalance,_tmpCurrency,_tmpIsAccountAwaiting)
          val _tmpCategory: CategoryEntity
          val _tmpCategoryId_1: Int
          _tmpCategoryId_1 = _stmt.getLong(_columnIndexOfCategoryId_1).toInt()
          val _tmpCategoryName: String
          _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          val _tmpEmoji: String
          _tmpEmoji = _stmt.getText(_columnIndexOfEmoji)
          val _tmpIsIncome: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsIncome).toInt()
          _tmpIsIncome = _tmp_3 != 0
          _tmpCategory = CategoryEntity(_tmpCategoryId_1,_tmpCategoryName,_tmpEmoji,_tmpIsIncome)
          _item = ExpenseFullInfo(_tmpExpense,_tmpAccount,_tmpCategory)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getExpensesAwaitingDispatch(): List<ExpenseFullInfo> {
    val _sql: String = """
        |SELECT *
        |        FROM expenses
        |        JOIN accounts ON expenses.account_id = accounts.accountId
        |        JOIN categories ON expenses.category_id = categories.categoryId
        |        WHERE (is_awaiting_dispatch_expense = 1 OR server_id IS NULL)
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _columnIndexOfServerId: Int = getColumnIndexOrThrow(_stmt, "server_id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "account_id")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "is_deleted")
        val _columnIndexOfIsExpenseAwaiting: Int = getColumnIndexOrThrow(_stmt,
            "is_awaiting_dispatch_expense")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfTransactionDate: Int = getColumnIndexOrThrow(_stmt, "transaction_date")
        val _columnIndexOfComment: Int = getColumnIndexOrThrow(_stmt, "comment")
        val _columnIndexOfAccountId_1: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfAccountName: Int = getColumnIndexOrThrow(_stmt, "account_name")
        val _columnIndexOfBalance: Int = getColumnIndexOrThrow(_stmt, "balance")
        val _columnIndexOfCurrency: Int = getColumnIndexOrThrow(_stmt, "currency")
        val _columnIndexOfIsAccountAwaiting: Int = getColumnIndexOrThrow(_stmt,
            "is_awaiting_dispatch_account")
        val _columnIndexOfCategoryId_1: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfEmoji: Int = getColumnIndexOrThrow(_stmt, "emoji")
        val _columnIndexOfIsIncome: Int = getColumnIndexOrThrow(_stmt, "is_income")
        val _result: MutableList<ExpenseFullInfo> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExpenseFullInfo
          val _tmpExpense: ExpenseEntity
          val _tmpLocalId: Int
          _tmpLocalId = _stmt.getLong(_columnIndexOfLocalId).toInt()
          val _tmpServerId: Int?
          if (_stmt.isNull(_columnIndexOfServerId)) {
            _tmpServerId = null
          } else {
            _tmpServerId = _stmt.getLong(_columnIndexOfServerId).toInt()
          }
          val _tmpAccountId: Int
          _tmpAccountId = _stmt.getLong(_columnIndexOfAccountId).toInt()
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId).toInt()
          val _tmpIsDeleted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp != 0
          val _tmpIsExpenseAwaiting: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsExpenseAwaiting).toInt()
          _tmpIsExpenseAwaiting = _tmp_1 != 0
          val _tmpAmount: String
          _tmpAmount = _stmt.getText(_columnIndexOfAmount)
          val _tmpTransactionDate: String
          _tmpTransactionDate = _stmt.getText(_columnIndexOfTransactionDate)
          val _tmpComment: String?
          if (_stmt.isNull(_columnIndexOfComment)) {
            _tmpComment = null
          } else {
            _tmpComment = _stmt.getText(_columnIndexOfComment)
          }
          _tmpExpense =
              ExpenseEntity(_tmpLocalId,_tmpServerId,_tmpAccountId,_tmpCategoryId,_tmpIsDeleted,_tmpIsExpenseAwaiting,_tmpAmount,_tmpTransactionDate,_tmpComment)
          val _tmpAccount: AccountEntity
          val _tmpAccountId_1: Int
          _tmpAccountId_1 = _stmt.getLong(_columnIndexOfAccountId_1).toInt()
          val _tmpAccountName: String
          _tmpAccountName = _stmt.getText(_columnIndexOfAccountName)
          val _tmpBalance: String
          _tmpBalance = _stmt.getText(_columnIndexOfBalance)
          val _tmpCurrency: String
          _tmpCurrency = _stmt.getText(_columnIndexOfCurrency)
          val _tmpIsAccountAwaiting: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsAccountAwaiting).toInt()
          _tmpIsAccountAwaiting = _tmp_2 != 0
          _tmpAccount =
              AccountEntity(_tmpAccountId_1,_tmpAccountName,_tmpBalance,_tmpCurrency,_tmpIsAccountAwaiting)
          val _tmpCategory: CategoryEntity
          val _tmpCategoryId_1: Int
          _tmpCategoryId_1 = _stmt.getLong(_columnIndexOfCategoryId_1).toInt()
          val _tmpCategoryName: String
          _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          val _tmpEmoji: String
          _tmpEmoji = _stmt.getText(_columnIndexOfEmoji)
          val _tmpIsIncome: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsIncome).toInt()
          _tmpIsIncome = _tmp_3 != 0
          _tmpCategory = CategoryEntity(_tmpCategoryId_1,_tmpCategoryName,_tmpEmoji,_tmpIsIncome)
          _item = ExpenseFullInfo(_tmpExpense,_tmpAccount,_tmpCategory)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getExpenseById(localId: Int): ExpenseFullInfo {
    val _sql: String = """
        |SELECT *
        |        FROM expenses
        |        JOIN accounts ON expenses.account_id = accounts.accountId
        |        JOIN categories ON expenses.category_id = categories.categoryId
        |        WHERE localId = ? LIMIT 1
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, localId.toLong())
        val _columnIndexOfLocalId: Int = getColumnIndexOrThrow(_stmt, "localId")
        val _columnIndexOfServerId: Int = getColumnIndexOrThrow(_stmt, "server_id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "account_id")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "is_deleted")
        val _columnIndexOfIsExpenseAwaiting: Int = getColumnIndexOrThrow(_stmt,
            "is_awaiting_dispatch_expense")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfTransactionDate: Int = getColumnIndexOrThrow(_stmt, "transaction_date")
        val _columnIndexOfComment: Int = getColumnIndexOrThrow(_stmt, "comment")
        val _columnIndexOfAccountId_1: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfAccountName: Int = getColumnIndexOrThrow(_stmt, "account_name")
        val _columnIndexOfBalance: Int = getColumnIndexOrThrow(_stmt, "balance")
        val _columnIndexOfCurrency: Int = getColumnIndexOrThrow(_stmt, "currency")
        val _columnIndexOfIsAccountAwaiting: Int = getColumnIndexOrThrow(_stmt,
            "is_awaiting_dispatch_account")
        val _columnIndexOfCategoryId_1: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfEmoji: Int = getColumnIndexOrThrow(_stmt, "emoji")
        val _columnIndexOfIsIncome: Int = getColumnIndexOrThrow(_stmt, "is_income")
        val _result: ExpenseFullInfo
        if (_stmt.step()) {
          val _tmpExpense: ExpenseEntity
          val _tmpLocalId: Int
          _tmpLocalId = _stmt.getLong(_columnIndexOfLocalId).toInt()
          val _tmpServerId: Int?
          if (_stmt.isNull(_columnIndexOfServerId)) {
            _tmpServerId = null
          } else {
            _tmpServerId = _stmt.getLong(_columnIndexOfServerId).toInt()
          }
          val _tmpAccountId: Int
          _tmpAccountId = _stmt.getLong(_columnIndexOfAccountId).toInt()
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId).toInt()
          val _tmpIsDeleted: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp != 0
          val _tmpIsExpenseAwaiting: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsExpenseAwaiting).toInt()
          _tmpIsExpenseAwaiting = _tmp_1 != 0
          val _tmpAmount: String
          _tmpAmount = _stmt.getText(_columnIndexOfAmount)
          val _tmpTransactionDate: String
          _tmpTransactionDate = _stmt.getText(_columnIndexOfTransactionDate)
          val _tmpComment: String?
          if (_stmt.isNull(_columnIndexOfComment)) {
            _tmpComment = null
          } else {
            _tmpComment = _stmt.getText(_columnIndexOfComment)
          }
          _tmpExpense =
              ExpenseEntity(_tmpLocalId,_tmpServerId,_tmpAccountId,_tmpCategoryId,_tmpIsDeleted,_tmpIsExpenseAwaiting,_tmpAmount,_tmpTransactionDate,_tmpComment)
          val _tmpAccount: AccountEntity
          val _tmpAccountId_1: Int
          _tmpAccountId_1 = _stmt.getLong(_columnIndexOfAccountId_1).toInt()
          val _tmpAccountName: String
          _tmpAccountName = _stmt.getText(_columnIndexOfAccountName)
          val _tmpBalance: String
          _tmpBalance = _stmt.getText(_columnIndexOfBalance)
          val _tmpCurrency: String
          _tmpCurrency = _stmt.getText(_columnIndexOfCurrency)
          val _tmpIsAccountAwaiting: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsAccountAwaiting).toInt()
          _tmpIsAccountAwaiting = _tmp_2 != 0
          _tmpAccount =
              AccountEntity(_tmpAccountId_1,_tmpAccountName,_tmpBalance,_tmpCurrency,_tmpIsAccountAwaiting)
          val _tmpCategory: CategoryEntity
          val _tmpCategoryId_1: Int
          _tmpCategoryId_1 = _stmt.getLong(_columnIndexOfCategoryId_1).toInt()
          val _tmpCategoryName: String
          _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          val _tmpEmoji: String
          _tmpEmoji = _stmt.getText(_columnIndexOfEmoji)
          val _tmpIsIncome: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsIncome).toInt()
          _tmpIsIncome = _tmp_3 != 0
          _tmpCategory = CategoryEntity(_tmpCategoryId_1,_tmpCategoryName,_tmpEmoji,_tmpIsIncome)
          _result = ExpenseFullInfo(_tmpExpense,_tmpAccount,_tmpCategory)
        } else {
          error("The query result was empty, but expected a single row to return a NON-NULL object of type <com.example.database.entity.expense.ExpenseFullInfo>.")
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateExpenseByLocalId(
    localId: Int,
    serverId: Int?,
    amount: String,
    comment: String?,
    transactionDate: String,
    accountId: Int,
    categoryId: Int,
  ): Int {
    val _sql: String =
        "UPDATE expenses SET is_awaiting_dispatch_expense = 1, account_id = ?, category_id = ?, transaction_date = ?, amount = ?, server_id = ?, comment = ? WHERE localId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, accountId.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId.toLong())
        _argIndex = 3
        _stmt.bindText(_argIndex, transactionDate)
        _argIndex = 4
        _stmt.bindText(_argIndex, amount)
        _argIndex = 5
        if (serverId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, serverId.toLong())
        }
        _argIndex = 6
        if (comment == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, comment)
        }
        _argIndex = 7
        _stmt.bindLong(_argIndex, localId.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateExpenseAwaitingStatus(localId: Int, serverId: Int?): Int {
    val _sql: String =
        "UPDATE expenses SET is_awaiting_dispatch_expense = 0, server_id = ? WHERE localId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (serverId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, serverId.toLong())
        }
        _argIndex = 2
        _stmt.bindLong(_argIndex, localId.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteExpense(localId: Int, serverId: Int?): Int {
    val _sql: String =
        "UPDATE expenses SET is_deleted = 1, is_awaiting_dispatch_expense = IIF(? IS NULL, 0, 1) WHERE localId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (serverId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, serverId.toLong())
        }
        _argIndex = 2
        _stmt.bindLong(_argIndex, localId.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
