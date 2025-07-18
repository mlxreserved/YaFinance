package com.example.income.internal.ui.income

import androidx.lifecycle.viewModelScope
import com.example.domain.model.income.Income
import com.example.domain.usecase.income.inter.GetIncomesUseCase
import com.example.ui.baseViewModel.BaseViewModel
import com.example.ui.data.state.ScreenState
import com.example.model.result.Result
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject


class IncomesViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase
) : BaseViewModel<List<Income>>() {
    fun getTodayIncomes(isRetried: Boolean = false) {
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

            when (val response = getIncomesUseCase.getIncomes(currentStartDate, currentEndDate)) {
                is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                is Result.Success<List<Income>> -> updateStateBasedOnListContent(response.result)
            }
        }
    }

    fun onRetryClicked() {
        getTodayIncomes(isRetried = true)
    }
}