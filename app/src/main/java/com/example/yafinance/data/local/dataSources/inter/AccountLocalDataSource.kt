package com.example.yafinance.data.local.dataSources.inter

import kotlinx.coroutines.flow.Flow

/** Получение и сохранение информации о счете локально **/
interface AccountLocalDataSource {

    val getAccountId: Flow<Int?>

    suspend fun saveAccountId(accountId: Int)

}