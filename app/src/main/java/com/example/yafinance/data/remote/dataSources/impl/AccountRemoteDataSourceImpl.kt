package com.example.yafinance.data.remote.dataSources.impl

import com.example.yafinance.data.remote.api.FinanceApi
import com.example.yafinance.data.remote.dataSources.inter.AccountRemoteDataSource
import com.example.yafinance.data.remote.models.account.AccountDTO
import com.example.yafinance.data.remote.models.account.AccountRequestDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**Получение и отправка данных о счете в сеть**/
class AccountRemoteDataSourceImpl @Inject constructor(
    private val financeApi: FinanceApi
) : AccountRemoteDataSource {

    /** Получение аккаунт **/
    override suspend fun getAccount(): AccountDTO = withContext(Dispatchers.IO) {
        financeApi.getAccounts().first()
    }

    /** Изменение информации об аккаунте **/
    override suspend fun changeAccountInfo(
        id: Int,
        accountRequest: AccountRequestDTO
    ): AccountDTO = withContext(Dispatchers.IO) {
        financeApi.changeAccountInfo(id = id, accountRequest = accountRequest)
    }
}