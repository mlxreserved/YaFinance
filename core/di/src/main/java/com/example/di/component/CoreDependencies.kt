package com.example.di.component

import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.network.api.AccountApi
import com.example.network.api.CategoryApi
import com.example.network.api.TransactionApi

interface CoreDependencies {
    fun transactionApi(): TransactionApi
    fun categoryApi(): CategoryApi
    fun accountApi(): AccountApi
    fun getAccountIdUseCase(): GetAccountIdUseCase
}