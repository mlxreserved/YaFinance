package com.example.domain.usecase.global.inter

import kotlinx.coroutines.flow.StateFlow

interface GetCurrentAccountNameUseCase {
    fun getAccountName(): StateFlow<String>
}