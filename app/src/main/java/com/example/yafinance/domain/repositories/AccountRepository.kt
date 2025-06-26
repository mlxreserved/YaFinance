package com.example.yafinance.domain.repositories

import com.example.yafinance.domain.utils.Result
import com.example.yafinance.domain.models.account.Account

/**
 * Репозиторий для работы со счетом
 **/
interface AccountRepository {
    /**
     * Получить счет с информацией о нем
     * **/
    suspend fun getAccount(): Result<Account>

    /** Изменить информацию счета **/
    suspend fun changeAccountInfo(id: Int, accountRequest: Account): Result<Account>

    /** Получить ID счета **/
    suspend fun getAccountId(): Result<Int>
}