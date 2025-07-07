package com.example.domain.repository.account

import kotlinx.coroutines.flow.Flow

interface AccountDataStore {
    val getAccountId: Flow<Int?>

    suspend fun saveAccountId(accountId: Int)
}