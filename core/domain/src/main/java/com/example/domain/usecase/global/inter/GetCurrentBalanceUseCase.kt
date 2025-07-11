package com.example.domain.usecase.global.inter

import kotlinx.coroutines.flow.StateFlow

interface GetCurrentBalanceUseCase {
    fun getBalance(): StateFlow<String>
}