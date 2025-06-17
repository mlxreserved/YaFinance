package com.example.yafinance.ui.screens.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.domain.usecase.inter.GetIncomesUseCase
import com.example.yafinance.ui.utils.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class IncomesViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase
): ViewModel() {

    private val _incomesState: MutableStateFlow<ScreenState<List<Income>>> =
        MutableStateFlow(ScreenState.Loading)
    val incomesState: StateFlow<ScreenState<List<Income>>> = _incomesState.asStateFlow()

    init {
        getIncomes()
    }

    private fun getIncomes() {
        viewModelScope.launch {
            _incomesState.update { ScreenState.Loading }

            try {
                val currentStartDate = Date()
                val currentEndDate = Date()

                val incomes = getIncomesUseCase.getIncomes(currentStartDate, currentEndDate)

                if(incomes.isEmpty()) {
                    _incomesState.update { ScreenState.Empty }
                } else {
                    _incomesState.update { ScreenState.Success(incomes) }
                }
            } catch (e: Exception) {
                _incomesState.update { ScreenState.Error(e.message ?: "") }
            }
        }
    }

}