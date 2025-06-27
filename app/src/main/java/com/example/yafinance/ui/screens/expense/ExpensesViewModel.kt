package com.example.yafinance.ui.screens.expense

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.usecase.inter.GetExpensesUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.BaseViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class ExpensesViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : BaseViewModel<List<Expense>>() {
    init {
        getTodayExpenses()
    }

    fun getTodayExpenses(isRetried: Boolean = false) {
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