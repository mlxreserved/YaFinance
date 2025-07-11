package com.example.edittransaction.internal.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.income.IncomeDetailed
import com.example.domain.usecase.expense.inter.GetExpenseByIdUseCase
import com.example.domain.usecase.income.inter.GetIncomeByIdUseCase
import com.example.model.result.Result
import com.example.ui.baseViewModel.BaseViewModel
import com.example.ui.data.state.ScreenState
import com.example.ui.data.state.ScreenState.Loading
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditTransactionViewModel(
    private val getIncomeByIdUseCase: GetIncomeByIdUseCase,
    private val getExpenseByIdUseCase: GetExpenseByIdUseCase,
) : ViewModel() {

    private val _incomeState = MutableStateFlow<ScreenState<IncomeDetailed>>(Loading)
    val incomeState: StateFlow<ScreenState<IncomeDetailed>> = _incomeState.asStateFlow()

    private val _expenseState = MutableStateFlow<ScreenState<ExpenseDetailed>>(Loading)
    val expenseState: StateFlow<ScreenState<ExpenseDetailed>> = _expenseState.asStateFlow()

    fun onIncomeEnter(id: Int, isAdd: Boolean) {
        viewModelScope.launch {
            if(isAdd){
                updateIncomeState(ScreenState.Empty)
            } else {
                val response = getIncomeByIdUseCase.getIncomeById(id)
                when (response) {
                    is Result.Error ->
                        updateIncomeState(ScreenState.Error(message = response.error))

                    is Result.Success<IncomeDetailed> ->
                        updateIncomeState(ScreenState.Success(result = response.result))
                }
            }
        }
    }

    fun onExpenseEnter(id: Int, isAdd: Boolean) {
        viewModelScope.launch {
            if(isAdd) {
                updateExpenseState(ScreenState.Empty)
            } else  {
                val response = getExpenseByIdUseCase.getExpenseById(id)
                when (response) {
                    is Result.Error ->
                        updateExpenseState(ScreenState.Error(message = response.error))

                    is Result.Success<ExpenseDetailed> ->
                        updateExpenseState(ScreenState.Success(result = response.result))
                }
            }
        }
    }

    private fun updateIncomeState(state: ScreenState<IncomeDetailed>) {
        _incomeState.update { state }
    }

    private fun updateExpenseState(state: ScreenState<ExpenseDetailed>) {
        _expenseState.update { state }
    }
}