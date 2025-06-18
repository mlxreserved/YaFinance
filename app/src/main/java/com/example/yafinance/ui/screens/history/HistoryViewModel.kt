package com.example.yafinance.ui.screens.history

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.yafinance.domain.usecase.inter.GetExpensesUseCase
import com.example.yafinance.domain.usecase.inter.GetIncomesUseCase
import com.example.yafinance.ui.utils.HistoryType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getIncomesUseCase: GetIncomesUseCase,
    private val getExpensesUseCase: GetExpensesUseCase
) : ViewModel() {

    val historyType: HistoryType by lazy {
        savedStateHandle.get<HistoryType>("historyType") ?: HistoryType.EXPENSES
    }

    var count = 0

    init {
        get()
    }

    fun get() {
        Log.d("HISTORY", "$historyType $count")
    }

}