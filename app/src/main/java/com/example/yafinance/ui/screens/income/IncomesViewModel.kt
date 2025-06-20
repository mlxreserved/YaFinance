package com.example.yafinance.ui.screens.income

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.domain.usecase.inter.GetIncomesUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.BaseViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class IncomesViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase,
) : BaseViewModel<List<Income>>() {

    init {
        getTodayIncomes()
    }

    private fun getTodayIncomes(countErrors: Int = 0) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)

            val currentStartDate = Date()
            val currentEndDate = Date()

            when(val response = getIncomesUseCase.getIncomes(currentStartDate, currentEndDate)){
                is Result.Error -> updateState(ScreenState.Error(response.error, countErrors))
                is Result.Success<List<Income>> -> updateStateBasedOnListContent(response.result)
            }
        }
    }

    fun onRetryClicked() {
        getTodayIncomes(countErrors = 1)
    }

}