package com.example.yafinance.domain.usecase.account.inter

import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.utils.Result

/** UseCase для получения информации об аккаунте**/
interface GetAccountUseCase {
    suspend fun getAccounts(): Result<Account>
}