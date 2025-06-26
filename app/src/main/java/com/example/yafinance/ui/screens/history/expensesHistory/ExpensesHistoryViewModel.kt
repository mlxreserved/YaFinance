package com.example.yafinance.ui.screens.history.expensesHistory

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.usecase.inter.GetExpensesUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.history.BaseHistoryViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class ExpensesHistoryViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : BaseHistoryViewModel<List<Expense>>() {

    init {
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

    fun onRetryClicked() {
        getHistory(isRetried = true)
    }

}