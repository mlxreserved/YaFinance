package com.example.edittransaction.internal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.category.Category
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.domain.model.income.IncomeDetailed
import com.example.domain.model.income.IncomeUpdate
import com.example.domain.usecase.category.inter.GetCategoriesByTypeUseCase
import com.example.domain.usecase.expense.inter.CreateExpenseUseCase
import com.example.domain.usecase.expense.inter.GetExpenseByIdUseCase
import com.example.domain.usecase.expense.inter.UpdateExpenseByIdUseCase
import com.example.domain.usecase.income.inter.CreateIncomeUseCase
import com.example.domain.usecase.income.inter.GetIncomeByIdUseCase
import com.example.domain.usecase.income.inter.UpdateIncomeByIdUseCase
import com.example.model.errorModel.ErrorModel
import com.example.model.result.Result
import com.example.ui.data.state.ScreenState
import com.example.ui.data.state.ScreenState.Loading
import com.example.utils.extensions.string.toLongDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

sealed interface ResponseOfEdit {
    object Success: ResponseOfEdit
    data class Error(val message: ErrorModel): ResponseOfEdit
    object Loading: ResponseOfEdit
}

class EditTransactionViewModel @Inject constructor(
    private val getIncomeByIdUseCase: GetIncomeByIdUseCase,
    private val getExpenseByIdUseCase: GetExpenseByIdUseCase,
    private val updateExpenseByIdUseCase: UpdateExpenseByIdUseCase,
    private val updateIncomeByIdUseCase: UpdateIncomeByIdUseCase,
    private val getCategoryByTypeUseCase: GetCategoriesByTypeUseCase,
    private val createIncomeUseCase: CreateIncomeUseCase,
    private val createExpenseUseCase: CreateExpenseUseCase
) : ViewModel() {

    private val _selectedStartDate = MutableStateFlow(getCalendarDate())
    val selectedStartDate: StateFlow<Date> = _selectedStartDate.asStateFlow()

    private val _updateState = MutableStateFlow<ResponseOfEdit>(ResponseOfEdit.Loading)
    val updateState: StateFlow<ResponseOfEdit> = _updateState.asStateFlow()

    private val _categoryState = MutableStateFlow<ScreenState<List<Category>>>(Loading)
    val categoryState: StateFlow<ScreenState<List<Category>>> = _categoryState.asStateFlow()

    private val _incomeState = MutableStateFlow<ScreenState<IncomeDetailed>>(Loading)
    val incomeState: StateFlow<ScreenState<IncomeDetailed>> = _incomeState.asStateFlow()

    private val _expenseState = MutableStateFlow<ScreenState<ExpenseDetailed>>(Loading)
    val expenseState: StateFlow<ScreenState<ExpenseDetailed>> = _expenseState.asStateFlow()

    fun onIncomeEnter(id: Int?, isAdd: Boolean, isRetried: Boolean = false) {
        viewModelScope.launch {
            if(isAdd){
                updateIncomeState(ScreenState.Empty)
            } else if(id != null) {
                updateExpenseState(Loading)
                val response = getIncomeByIdUseCase.getIncomeById(id)
                when (response) {
                    is Result.Error ->
                        updateIncomeState(ScreenState.Error(message = response.error, isRetried = isRetried))

                    is Result.Success<IncomeDetailed> -> {
                        updateIncomeState(ScreenState.Success(result = response.result))
                        _selectedStartDate.update { response.result.transactionDate.toLongDate() }
                    }
                }
            }
        }
    }

    fun onExpenseEnter(id: Int?, isAdd: Boolean, isRetried: Boolean = false) {
        viewModelScope.launch {
            if(isAdd) {
                updateExpenseState(ScreenState.Empty)
            } else if(id != null) {
                updateExpenseState(Loading)
                val response = getExpenseByIdUseCase.getExpenseById(id)
                when (response) {
                    is Result.Error ->
                        updateExpenseState(ScreenState.Error(message = response.error, isRetried = isRetried))

                    is Result.Success<ExpenseDetailed> -> {
                        updateExpenseState(ScreenState.Success(result = response.result))
                        _selectedStartDate.update { response.result.transactionDate.toLongDate() }

                    }
                }
            }
        }
    }

    fun updateExpense(id: Int, expense: ExpenseUpdate) {
        viewModelScope.launch {
            _updateState.update { ResponseOfEdit.Loading }
            when(val response = updateExpenseByIdUseCase.updateExpenseById(id, expense)) {
                is Result.Error -> _updateState.update { ResponseOfEdit.Error(response.error) }
                is Result.Success<*> -> _updateState.update { ResponseOfEdit.Success }
            }
        }
    }

    fun updateIncome(id: Int, income: IncomeUpdate) {
        viewModelScope.launch {
            _updateState.update { ResponseOfEdit.Loading }
            when(val response = updateIncomeByIdUseCase.updateIncomeById(id, income)) {
                is Result.Error -> _updateState.update { ResponseOfEdit.Error(response.error) }
                is Result.Success<*> -> _updateState.update { ResponseOfEdit.Success }
            }
        }
    }

    fun createIncome(income: IncomeUpdate) {
        viewModelScope.launch {
            _updateState.update { ResponseOfEdit.Loading }
            when(val response = createIncomeUseCase.createIncome(income)) {
                is Result.Error -> _updateState.update { ResponseOfEdit.Error(response.error) }
                is Result.Success<*> -> _updateState.update { ResponseOfEdit.Success }
            }
        }
    }

    fun createExpense(expense: ExpenseUpdate) {
        viewModelScope.launch {
            _updateState.update { ResponseOfEdit.Loading }
            when(val response = createExpenseUseCase.createExpense(expense)) {
                is Result.Error -> _updateState.update { ResponseOfEdit.Error(response.error) }
                is Result.Success<*> -> _updateState.update { ResponseOfEdit.Success }
            }
        }
    }

    fun onCategoryClick(isIncome: Boolean) {
        viewModelScope.launch {
            updateCategoryState(Loading)
            val response = getCategoryByTypeUseCase.getCategoriesByType(isIncome)
            when(response) {
                is Result.Error ->
                    updateCategoryState(ScreenState.Error(message = response.error))
                is Result.Success<List<Category>> ->{
                    if(response.result.isEmpty()){
                        updateCategoryState(ScreenState.Empty)
                    } else {
                        updateCategoryState(ScreenState.Success(result = response.result))
                    }
                }
            }
        }
    }

    fun updateStartDate(newStartDateMillis: Long? = null, hours: Int? = null, minutes: Int? = null) {
        _selectedStartDate.update { currentDate ->
            Calendar.getInstance().apply {
                time = if(hours == null && minutes == null) {
                    newStartDateMillis?.let { Date(it) } ?: getCalendarDate()
                } else {
                    newStartDateMillis?.let { Date(it) } ?: currentDate
                }

                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 1)

                if(newStartDateMillis == null) {
                    hours?.let { set(Calendar.HOUR_OF_DAY, it) } ?: set(Calendar.HOUR_OF_DAY, 0)
                    minutes?.let { set(Calendar.MINUTE, it) } ?: set(Calendar.MINUTE, 0)
                }
            }.time
        }
    }

    private fun getCalendarDate(): Date = Date()

    private fun updateIncomeState(state: ScreenState<IncomeDetailed>) {
        _incomeState.update { state }
    }

    private fun updateExpenseState(state: ScreenState<ExpenseDetailed>) {
        _expenseState.update { state }
    }

    private fun updateCategoryState(state: ScreenState<List<Category>>) {
        _categoryState.update { state }
    }

    fun onIncomeTransactionRetry(id: Int?, isAdd: Boolean) {
        onIncomeEnter(id = id, isAdd = isAdd, isRetried = true)
    }

    fun onExpenseTransactionRetry(id: Int?, isAdd: Boolean) {
        onIncomeEnter(id = id, isAdd = isAdd, isRetried = true)
    }
}