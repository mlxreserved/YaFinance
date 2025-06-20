package com.example.yafinance.ui.screens.expense

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.usecase.inter.GetExpensesUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.BaseViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : BaseViewModel<List<Expense>>() {

    init {
        getTodayExpenses()
    }

    fun getTodayExpenses(countErrors: Int = 0) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            val currentStartDate = Date()
            val currentEndDate = Date()

            when(val response = getExpensesUseCase.getExpenses(currentStartDate, currentEndDate)) {
                is Result.Error -> updateState(ScreenState.Error(response.error, countErrors))
                is Result.Success<List<Expense>> -> updateStateBasedOnListContent(response.result)
            }
        }
    }

    fun onRetryClicked() {
        getTodayExpenses(countErrors = 1)
    }
}