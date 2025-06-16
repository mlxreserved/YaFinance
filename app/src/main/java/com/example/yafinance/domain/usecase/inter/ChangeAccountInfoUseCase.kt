package com.example.yafinance.domain.usecase.inter

import com.example.yafinance.domain.models.account.Account

interface ChangeAccountInfoUseCase {
    suspend fun changeAccountInfo(id: Int, accountRequest: Account): Account
}