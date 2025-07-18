package com.example.expense.internal.ui.expensesAnalyse

import androidx.lifecycle.viewModelScope
import com.example.domain.model.expense.Expense
import com.example.domain.usecase.expense.inter.GetExpensesUseCase
import com.example.model.categoryTotal.CategoryTotal
import com.example.model.result.Result
import com.example.ui.baseViewModel.BaseHistoryViewModel
import com.example.ui.data.state.ScreenState
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class ExpenseAnalyseViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : BaseHistoryViewModel<List<CategoryTotal>>() {
    override fun getHistory(
        startDate: Date?,
        endDate: Date?,
        isRetried: Boolean
    ) {
        viewModelScope.launch {
            updateState(ScreenState.Loading)
            if (endDate == null) {
                val currentEndDate = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 23)
                    set(Calendar.MINUTE, 59)
                    set(Calendar.SECOND, 59)
                    set(Calendar.MILLISECOND, 0)
                }.time
                when (val response = getExpensesUseCase.getExpenses(startDate, currentEndDate)) {
                    is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                    is Result.Success<List<Expense>> -> {
                        val result = response.result
                        val totalSum = result.sumOf { it.amount.toDouble() }
                        val totalByCategory: Map<Int, Double> = result
                            .groupBy { it.categoryId }
                            .mapValues { (_, expenses) -> expenses.sumOf { it.amount.toDouble() } }
                        val resultList = totalByCategory.map { (categoryId, total) ->
                            val category = result.find { it.categoryId == categoryId }
                            CategoryTotal(
                                categoryId = categoryId,
                                totalAmount = total,
                                currency = category!!.currency,
                                categoryName = category.title,
                                leadIcon = category.leadIcon,
                                percentage = "${"%.2f".format((total / totalSum * 100))}%"
                            )
                        }
                        updateStateBasedOnListContent(resultList)
                    }
                }
            } else {
                when (val response = getExpensesUseCase.getExpenses(startDate, endDate)) {
                    is Result.Error -> updateState(ScreenState.Error(response.error, isRetried))
                    is Result.Success<List<Expense>> -> {
                        val result = response.result
                        val totalSum = result.sumOf { it.amount.toDouble() }
                        val totalByCategory: Map<Int, Double> = result
                            .groupBy { it.categoryId }
                            .mapValues { (_, expenses) -> expenses.sumOf { it.amount.toDouble() } }
                        val resultList = totalByCategory.map { (categoryId, total) ->
                            val category = result.find { it.categoryId == categoryId }
                            CategoryTotal(
                                categoryId = categoryId,
                                totalAmount = total,
                                currency = category!!.currency,
                                categoryName = category.title,
                                leadIcon = category.leadIcon,
                                percentage = "${"%.2f".format((total / totalSum * 100))}%"
                            )
                        }
                        updateStateBasedOnListContent(resultList)
                    }
                }
            }
        }
    }

    fun onRetryClicked() {
        getHistory(isRetried = true)
    }
}