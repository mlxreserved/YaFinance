package com.example.yafinance.data.local.dataSources.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.yafinance.data.local.dataSources.inter.AccountLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountLocalDataSourceImpl(
    private val context: Context
) : AccountLocalDataSource {
    override val getAccountId: Flow<Int?>
        get() = context.dataStore.data.map { preferences ->
            preferences[ACCOUNT_ID_KEY]
        }

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