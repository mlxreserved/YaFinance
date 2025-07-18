package com.example.income.internal.ui.incomesHistory

import androidx.lifecycle.viewModelScope
import com.example.domain.model.income.Income
import com.example.domain.usecase.income.inter.GetIncomesUseCase
import com.example.ui.baseViewModel.BaseHistoryViewModel
import com.example.ui.data.state.ScreenState
import com.example.model.result.Result
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class IncomesHistoryViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase
) : BaseHistoryViewModel<List<Income>>() {

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
                when (val response = getIncomesUseCase.getIncomes(startDate, currentEndDate)) {
                    is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                    is Result.Success<List<Income>> -> updateStateBasedOnListContent(response.result)
                }
            } else {
                when (val response = getIncomesUseCase.getIncomes(startDate, endDate)) {
                    is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                    is Result.Success<List<Income>> -> updateStateBasedOnListContent(response.result)
                }
            }
        }
    }

    fun onRetryClicked() {
        getHistory(isRetried = true)
    }
}