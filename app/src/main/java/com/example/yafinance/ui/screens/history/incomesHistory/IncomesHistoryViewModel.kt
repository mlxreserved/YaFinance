package com.example.yafinance.ui.screens.history.incomesHistory

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.domain.usecase.inter.GetIncomesUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.history.BaseHistoryViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class IncomesHistoryViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase
) : BaseHistoryViewModel<List<Income>>() {

    init {
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

    fun onRetryClicked() {
        getHistory(isRetried = true)
    }

}