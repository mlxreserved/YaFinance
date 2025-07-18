package com.example.data.repository.account

import com.example.data.mappers.toAccountRequestDTO
import com.example.data.mappers.toDomain
import com.example.data.mappers.toEntity
import com.example.data.mappers.toTransactionRequest
import com.example.database.dao.AccountDao
import com.example.domain.model.account.Account
import com.example.domain.repository.account.AccountRepository
import com.example.model.result.Result
import com.example.network.api.AccountApi
import com.example.network.utils.safeCall.safeCallWithRetry
import com.example.utils.extensions.string.formatWithoutSpaces
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/** Репозиторий для работы со счетом **/
class AccountRepositoryImpl @Inject constructor(
    private val accountApi: AccountApi,
    private val accountDao: AccountDao
) : AccountRepository {
    /** Получить счет с информацией о нем **/
    override suspend fun getAccount(): Result<Account> {
        accountDao.getAccount().firstOrNull()?.let { account ->
            return Result.Success(account.toDomain())
        }

        return safeCallWithRetry {
            withContext(Dispatchers.IO) {
                accountApi.getAccounts().first().toDomain()
            }.also {
                accountDao.insertAccount(it.toEntity())
            }
        }
    }

    /** Изменить информацию счета **/
    override suspend fun changeAccountInfo(id: Int, accountRequest: Account): Result<Account> {
        accountDao.updateAccount(
            accountId = accountRequest.id,
            currency = accountRequest.currency,
            balance = accountRequest.sum.formatWithoutSpaces(),
            accountName = accountRequest.name
        )

        return safeCallWithRetry(
            maxRetries = 0
        ) {
            withContext(Dispatchers.IO) {
                accountApi.changeAccountInfo(
                    id = id,
                    accountRequest = accountRequest.toAccountRequestDTO()
                ).toDomain()
            }
        }.also {
            if(it is Result.Success) {
                accountDao.updateAccountAwaitingStatus(it.result.id)
            }
        }
    }

    /** Получить ID счета **/
    override suspend fun getAccountId(): Result<Int> {
        val resultLocal = checkLocalAccountInfo()
        if(resultLocal is Result.Success && resultLocal.result != EMPTY_ACCOUNT_INFO) {
            return resultLocal
        }

        return safeCallWithRetry(
            maxRetries = 0
        ) {
            withContext(Dispatchers.IO) {
                val result = accountApi.getAccounts()
                    .first()
                    .toDomain()

                accountDao.insertAccount(result.toEntity())

                result.id
            }
        }
    }

    override suspend fun syncLocalChanges() {
        accountDao.getAccount().firstOrNull()?.let { changedAccountInfo ->
            safeCallWithRetry{
                withContext(Dispatchers.IO) {
                    accountApi.changeAccountInfo(
                        id = changedAccountInfo.accountId,
                        accountRequest = changedAccountInfo.toTransactionRequest()
                    )
                }
            }
        }
    }

    private suspend fun checkLocalAccountInfo(): Result<Int> {
        accountDao.getAccount().firstOrNull()?.let { account ->
            return Result.Success(account.accountId)
        }
        return Result.Success(EMPTY_ACCOUNT_INFO)
    }

    companion object {
        private const val EMPTY_ACCOUNT_INFO = -1
    }
}