package com.example.di.component

import com.example.domain.usecase.account.inter.GetAccountIdUseCase
import com.example.domain.usecase.global.inter.GetCurrentAccountNameUseCase
import com.example.domain.usecase.global.inter.GetCurrentBalanceUseCase
import com.example.domain.usecase.global.inter.GetCurrentCurrencyUseCase
import com.example.domain.usecase.global.inter.SetCurrentAccountNameUseCase
import com.example.domain.usecase.global.inter.SetCurrentBalanceUseCase
import com.example.domain.usecase.global.inter.SetCurrentCurrencyUseCase
import com.example.network.api.AccountApi
import com.example.network.api.CategoryApi
import com.example.network.api.TransactionApi


interface CoreDependencies {
    fun transactionApi(): TransactionApi
    fun categoryApi(): CategoryApi
    fun accountApi(): AccountApi
    fun getAccountIdUseCase(): GetAccountIdUseCase
    fun getAccountName(): GetCurrentAccountNameUseCase
    fun getBalance(): GetCurrentBalanceUseCase
    fun getCurrency(): GetCurrentCurrencyUseCase
    fun setAccountName(): SetCurrentAccountNameUseCase
    fun setBalance(): SetCurrentBalanceUseCase
    fun setCurrency(): SetCurrentCurrencyUseCase
}