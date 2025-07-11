package com.example.domain.usecase.account.inter

import com.example.domain.model.account.Account
import com.example.model.result.Result

/** UseCase для получения информации об аккаунте**/
interface GetAccountUseCase {
    suspend fun getAccounts(): Result<Account>
}