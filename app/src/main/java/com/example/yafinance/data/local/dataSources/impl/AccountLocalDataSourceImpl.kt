package com.example.yafinance.data.local.dataSources.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.yafinance.data.local.dataSources.inter.AccountLocalDataSource
import com.example.yafinance.di.module.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/** Получение и сохранение информации о счете локально **/
class AccountLocalDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : AccountLocalDataSource {

    /** Получение ID счета **/
    override val getAccountId: Flow<Int?>
        get() = context.dataStore.data.map { preferences ->
            preferences[ACCOUNT_ID_KEY]
        }

    /** Сохранение ID счета **/
    override suspend fun saveAccountId(accountId: Int) {
        context.dataStore.edit { preferences ->
            preferences[ACCOUNT_ID_KEY] = accountId
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("AccountId")
        private val ACCOUNT_ID_KEY = intPreferencesKey("account_id")
    }
}