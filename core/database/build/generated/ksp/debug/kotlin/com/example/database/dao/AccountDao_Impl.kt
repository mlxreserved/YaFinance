package com.example.database.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.database.entity.account.AccountEntity
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
public class AccountDao_Impl(
  __db: RoomDatabase,
) : AccountDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAccountEntity: EntityInsertAdapter<AccountEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAccountEntity = object : EntityInsertAdapter<AccountEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `accounts` (`accountId`,`account_name`,`balance`,`currency`,`is_awaiting_dispatch_account`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AccountEntity) {
        statement.bindLong(1, entity.accountId.toLong())
        statement.bindText(2, entity.accountName)
        statement.bindText(3, entity.balance)
        statement.bindText(4, entity.currency)
        val _tmp: Int = if (entity.isAccountAwaiting) 1 else 0
        statement.bindLong(5, _tmp.toLong())
      }
    }
  }

  public override suspend fun insertAccount(accountEntity: AccountEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfAccountEntity.insertAndReturnId(_connection, accountEntity)
    _result
  }

  public override suspend fun getAccount(): List<AccountEntity> {
    val _sql: String = "SELECT * FROM accounts"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfAccountName: Int = getColumnIndexOrThrow(_stmt, "account_name")
        val _columnIndexOfBalance: Int = getColumnIndexOrThrow(_stmt, "balance")
        val _columnIndexOfCurrency: Int = getColumnIndexOrThrow(_stmt, "currency")
        val _columnIndexOfIsAccountAwaiting: Int = getColumnIndexOrThrow(_stmt,
            "is_awaiting_dispatch_account")
        val _result: MutableList<AccountEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AccountEntity
          val _tmpAccountId: Int
          _tmpAccountId = _stmt.getLong(_columnIndexOfAccountId).toInt()
          val _tmpAccountName: String
          _tmpAccountName = _stmt.getText(_columnIndexOfAccountName)
          val _tmpBalance: String
          _tmpBalance = _stmt.getText(_columnIndexOfBalance)
          val _tmpCurrency: String
          _tmpCurrency = _stmt.getText(_columnIndexOfCurrency)
          val _tmpIsAccountAwaiting: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAccountAwaiting).toInt()
          _tmpIsAccountAwaiting = _tmp != 0
          _item =
              AccountEntity(_tmpAccountId,_tmpAccountName,_tmpBalance,_tmpCurrency,_tmpIsAccountAwaiting)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateAccount(
    accountId: Int,
    currency: String,
    balance: String,
    accountName: String,
  ): Int {
    val _sql: String =
        "UPDATE accounts SET is_awaiting_dispatch_account = 1, currency = ?, balance = ?, account_name = ? WHERE accountId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, currency)
        _argIndex = 2
        _stmt.bindText(_argIndex, balance)
        _argIndex = 3
        _stmt.bindText(_argIndex, accountName)
        _argIndex = 4
        _stmt.bindLong(_argIndex, accountId.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateAccountAwaitingStatus(accountId: Int): Int {
    val _sql: String = "UPDATE accounts SET is_awaiting_dispatch_account = 0 WHERE accountId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, accountId.toLong())
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
