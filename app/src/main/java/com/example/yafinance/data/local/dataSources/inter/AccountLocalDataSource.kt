package com.example.yafinance.data.local.dataSources.inter

import kotlinx.coroutines.flow.Flow


interface AccountLocalDataSource {
    val getAccountId: Flow<Int?>

    suspend fun saveAccountId(accountId: Int)
}