package com.example.expense.internal.ui.expense

import androidx.lifecycle.viewModelScope
import com.example.expense.di.scope.ExpenseScope
import com.example.domain.model.expense.Expense
import com.example.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.ui.baseViewModel.BaseViewModel
import com.example.ui.data.state.ScreenState
import com.example.model.result.Result
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@ExpenseScope
class ExpensesViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : BaseViewModel<List<Expense>>() {
    fun getTodayExpenses(isRetried: Boolean = false) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            val currentStartDate: Date = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.time
            val currentEndDate = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 23)
                set(Calendar.MINUTE, 59)
                set(Calendar.SECOND, 59)
                set(Calendar.MILLISECOND, 0)
            }.time

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