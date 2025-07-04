package com.example.yafinance.ui.screens.history.expensesHistory

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.usecase.global.inter.GetCurrentCurrencyUseCase
import com.example.yafinance.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.history.BaseHistoryViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class ExpensesHistoryViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase,
    getCurrentCurrencyUseCase: GetCurrentCurrencyUseCase
) : BaseHistoryViewModel<List<Expense>>() {
    private var currentCurrency = getCurrentCurrencyUseCase.getCurrency()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = "RUB"
        )

    init {
        observeCurrencyChanges()
        getHistory()
    }

    override fun getHistory(startDate: Date?, endDate: Date?, isRetried: Boolean) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            when (val response = getExpensesUseCase.getExpenses(startDate, endDate)) {
                is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                is Result.Success<List<Expense>> -> updateStateBasedOnListContent(response.result)
            }
        }
    }

    private fun observeCurrencyChanges() {
        viewModelScope.launch {
            currentCurrency.collect { currency ->
                val state = screenState.value
                if (state is ScreenState.Success) {
                    val updated = state.result.map { it.copy(currency = currency) }
                    updateState(ScreenState.Success(updated))
                }
            }
        }
    }

    fun onRetryClicked() {
        getHistory(isRetried = true)
    }
}