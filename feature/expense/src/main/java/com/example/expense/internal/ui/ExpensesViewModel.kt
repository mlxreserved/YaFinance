package com.example.expense.internal.ui

import androidx.lifecycle.viewModelScope
import com.example.expense.internal.di.scope.ExpenseScope
import com.example.domain.model.expense.Expense
import com.example.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.ui.baseViewModel.BaseViewModel
import com.example.ui.data.state.ScreenState
import com.example.model.result.Result
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@ExpenseScope
class ExpensesViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase,
//    getCurrentCurrencyUseCase: GetCurrentCurrencyUseCase
) : BaseViewModel<List<Expense>>() {
//    private var currentCurrency = getCurrentCurrencyUseCase.getCurrency()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000L),
//            initialValue = "RUB"
//        )

    init {
        observeCurrencyChanges()
        getTodayExpenses()
    }

    private fun observeCurrencyChanges() {
//        viewModelScope.launch {
//            currentCurrency.collect { currency ->
//                val state = screenState.value
//                if (state is ScreenState.Success) {
//                    val updated = state.result.map { it.copy(currency = currency) }
//                    updateState(ScreenState.Success(updated))
//                }
//            }
//        }
    }

    private fun getTodayExpenses(isRetried: Boolean = false) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            val currentStartDate = Date()
            val currentEndDate = Date()

            when (val response = getExpensesUseCase.getExpenses(currentStartDate, currentEndDate)) {
                is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                is Result.Success<List<Expense>> -> updateStateBasedOnListContent(response.result)
            }
        }
    }

    fun onRetryClicked() {
        getTodayExpenses(isRetried = true)
    }
}