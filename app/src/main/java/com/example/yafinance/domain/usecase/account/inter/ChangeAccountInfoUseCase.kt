package com.example.yafinance.domain.usecase.account.inter

import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.domain.utils.Result

/** UseCase для изменения информации об аккаунте **/
interface ChangeAccountInfoUseCase {
    suspend fun changeAccountInfo(id: Int, accountRequest: Account): Result<Account>
}