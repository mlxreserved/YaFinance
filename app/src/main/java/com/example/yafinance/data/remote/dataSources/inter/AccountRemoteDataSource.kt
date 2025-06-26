package com.example.yafinance.data.remote.dataSources.inter

import com.example.yafinance.data.remote.models.account.AccountDTO
import com.example.yafinance.data.remote.models.account.AccountRequestDTO


/**
 * Получение и отправка данных о счете в сеть
 * **/
interface AccountRemoteDataSource {

    suspend fun getAccount() : AccountDTO

    suspend fun changeAccountInfo(id: Int, accountRequest: AccountRequestDTO) : AccountDTO

}