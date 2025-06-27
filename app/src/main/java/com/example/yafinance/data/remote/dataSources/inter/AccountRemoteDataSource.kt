package com.example.yafinance.data.remote.dataSources.inter

import com.example.yafinance.data.remote.models.account.AccountDTO
import com.example.yafinance.data.remote.models.account.AccountRequestDTO


interface AccountRemoteDataSource {
    suspend fun getAccount(): AccountDTO

    suspend fun changeAccountInfo(id: Int, accountRequest: AccountRequestDTO): AccountDTO
}