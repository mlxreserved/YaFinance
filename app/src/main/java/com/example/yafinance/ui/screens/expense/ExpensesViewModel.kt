package com.example.yafinance.ui.screens.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.domain.usecase.inter.GetExpensesUseCase
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
class ExpensesViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : ViewModel() {

    private val _expensesState: MutableStateFlow<ScreenState<List<Expense>>> =
        MutableStateFlow(ScreenState.Loading)
    val expensesState: StateFlow<ScreenState<List<Expense>>> = _expensesState.asStateFlow()

    init {
        getExpenses()
    }

    private fun getExpenses() {
        viewModelScope.launch {
            _expensesState.update { ScreenState.Loading }

            try {
                val currentStartDate = Date()
                val currentEndDate = Date()

                val expenses = getExpensesUseCase.getExpenses(currentStartDate, currentEndDate)

                if(expenses.isEmpty()) {
                    _expensesState.update { ScreenState.Empty }
                } else {
                    _expensesState.update { ScreenState.Success(expenses) }
                }
            } catch (e: Exception) {
                _expensesState.update { ScreenState.Error(e.message ?: "") }
            }
        }
    }
}