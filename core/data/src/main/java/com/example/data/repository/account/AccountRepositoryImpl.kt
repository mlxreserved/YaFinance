package com.example.data.repository.account

import com.example.data.mappers.toAccountRequestDTO
import com.example.data.mappers.toDomain
import com.example.domain.model.account.Account
import com.example.domain.repository.account.AccountDataStore
import com.example.domain.repository.account.AccountRepository
import com.example.model.result.Result
import com.example.network.api.AccountApi
import com.example.network.utils.safeCall.safeCallWithRetry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

/** Репозиторий для работы со счетом **/
class AccountRepositoryImpl @Inject constructor(
    private val accountApi: AccountApi,
    private val accountDataStore: AccountDataStore
) : AccountRepository {
    /** Получить счет с информацией о нем **/
    override suspend fun getAccount(): Result<Account> =
        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                accountApi.getAccounts().first().toDomain()
            }
        }

    /** Изменить информацию счета **/
    override suspend fun changeAccountInfo(id: Int, accountRequest: Account): Result<Account> =
        safeCallWithRetry {
            accountApi.changeAccountInfo(
                id = id,
                accountRequest = accountRequest.toAccountRequestDTO()
            ).toDomain()
        }

    /** Получить ID счета **/
    override suspend fun getAccountId(): Result<Int> {
        accountDataStore.getAccountId.first()?.let { accountId ->
            return Result.Success(accountId)
        }

        return safeCallWithRetry {
            accountApi.getAccounts()
                .first()
                .toDomain()
                .also { account ->
                    accountDataStore.saveAccountId(account.id)
                }.id
        }

    }
}