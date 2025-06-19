package com.example.yafinance.ui.screens.history.incomesHistory

import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.domain.usecase.inter.GetIncomesUseCase
import com.example.yafinance.domain.utils.Result
import com.example.yafinance.ui.screens.history.BaseHistoryViewModel
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.toUserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class IncomesHistoryViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase
): BaseHistoryViewModel<List<Income>>() {

    init {
        getHistory()
    }

    override fun getHistory(startDate: Date?, endDate: Date?) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)
            when (val response = getIncomesUseCase.getIncomes(startDate, endDate)) {
                is Result.Error -> updateState(ScreenState.Error(response.error.toUserMessage()))
                is Result.Success<List<Income>> -> updateStateBasedOnListContent(response.result)
            }
        }
    }

}