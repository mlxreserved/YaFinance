package com.example.yafinance.data.remote.repositories

import com.example.yafinance.data.local.dataSources.inter.AccountLocalDataSource
import com.example.yafinance.data.remote.dataSources.inter.AccountRemoteDataSource
import com.example.yafinance.data.remote.mappers.toAccountRequestDTO
import com.example.yafinance.data.remote.mappers.toDomain
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.repositories.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.data.remote.utils.safeCallWithRetry
import kotlinx.coroutines.flow.first
import javax.inject.Inject


/** Репозиторий для работы со счетом **/
class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val accountLocalDataSource: AccountLocalDataSource
) : AccountRepository {


    /** Получить счет с информацией о нем **/
    override suspend fun getAccount(): Result<Account> =
        safeCallWithRetry {
            withContext(Dispatchers.IO) {
                accountRemoteDataSource.getAccount().toDomain()
            }
        }

    /** Изменить информацию счета **/
    override suspend fun changeAccountInfo(id: Int, accountRequest: Account): Result<Account> =
        safeCallWithRetry {
            accountRemoteDataSource.changeAccountInfo(
                id = id,
                accountRequest = accountRequest.toAccountRequestDTO()
            ).toDomain()
        }

    /** Получить ID счета **/
    override suspend fun getAccountId(): Result<Int> {
        accountLocalDataSource.getAccountId.first()?.let { accountId ->
            return Result.Success(accountId)
        }

        return safeCallWithRetry {
            accountRemoteDataSource.getAccount()
                .toDomain()
                .also { account ->
                    accountLocalDataSource.saveAccountId(account.id)
                }.id
        }

    }
}