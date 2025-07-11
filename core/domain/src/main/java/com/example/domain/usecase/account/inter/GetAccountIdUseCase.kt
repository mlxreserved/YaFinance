package com.example.domain.usecase.account.inter

import com.example.model.result.Result

interface GetAccountIdUseCase {
    suspend fun getAccountId(): Result<Int>
}