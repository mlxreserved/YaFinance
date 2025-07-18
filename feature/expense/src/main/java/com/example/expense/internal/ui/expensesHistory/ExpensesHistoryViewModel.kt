package com.example.expense.internal.ui.expensesHistory

import androidx.lifecycle.viewModelScope
import com.example.domain.model.expense.Expense
import com.example.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.ui.baseViewModel.BaseHistoryViewModel
import com.example.ui.data.state.ScreenState
import kotlinx.coroutines.launch
import com.example.model.result.Result
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class ExpensesHistoryViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : BaseHistoryViewModel<List<Expense>>() {

    override fun getHistory(startDate: Date?, endDate: Date?, isRetried: Boolean) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)
            if(endDate == null) {
                val currentEndDate = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 23)
                    set(Calendar.MINUTE, 59)
                    set(Calendar.SECOND, 59)
                    set(Calendar.MILLISECOND, 0)
                }.time
                when (val response = getExpensesUseCase.getExpenses(startDate, currentEndDate)) {
                    is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                    is Result.Success<List<Expense>> -> updateStateBasedOnListContent(response.result)
                }
            } else {
                when (val response = getExpensesUseCase.getExpenses(startDate, endDate)) {
                    is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                    is Result.Success<List<Expense>> -> updateStateBasedOnListContent(response.result)
                }
            }
        }
    }

    fun onRetryClicked() {
        getHistory(isRetried = true)
    }
}