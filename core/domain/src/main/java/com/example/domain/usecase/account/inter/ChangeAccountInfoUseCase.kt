package com.example.domain.usecase.account.inter

import com.example.domain.model.account.Account
import com.example.model.result.Result

/** UseCase для изменения информации об аккаунте **/
interface ChangeAccountInfoUseCase {
    suspend fun changeAccountInfo(id: Int, accountRequest: Account): Result<Account>
}