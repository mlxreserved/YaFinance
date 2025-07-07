package com.example.data.repository.global

import com.example.domain.repository.global.AccountNameProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountNameProviderImpl @Inject constructor() : AccountNameProvider {
    private val _currentAccountName = MutableStateFlow("")
    override val currentAccountName: StateFlow<String> = _currentAccountName.asStateFlow()

    override fun setAccountName(newAccountName: String) {
        _currentAccountName.value = newAccountName
    }
}