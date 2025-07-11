package com.example.income.internal.ui.incomesHistory

import androidx.lifecycle.viewModelScope
import com.example.domain.model.income.Income
import com.example.domain.usecase.global.inter.GetCurrentCurrencyUseCase
import com.example.domain.usecase.income.inter.GetIncomesUseCase
import com.example.ui.baseViewModel.BaseHistoryViewModel
import com.example.ui.data.state.ScreenState
import com.example.model.result.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class IncomesHistoryViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase,
    getCurrentCurrencyUseCase: GetCurrentCurrencyUseCase
) : BaseHistoryViewModel<List<Income>>() {
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
            when (val response = getIncomesUseCase.getIncomes(startDate, endDate)) {
                is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                is Result.Success<List<Income>> -> updateStateBasedOnListContent(response.result)
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