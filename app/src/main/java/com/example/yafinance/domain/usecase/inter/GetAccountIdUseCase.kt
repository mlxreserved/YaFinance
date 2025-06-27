package com.example.yafinance.domain.usecase.inter

import com.example.yafinance.domain.utils.Result

/** UseCase для получения ID счета**/
interface GetAccountIdUseCase {
    suspend fun getAccountId(): Result<Int>
}