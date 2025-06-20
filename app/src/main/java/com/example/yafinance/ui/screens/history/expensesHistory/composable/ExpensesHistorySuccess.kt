package com.example.yafinance.ui.screens.history.expensesHistory.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.ui.composable.datePicker.CustomDatePicker
import com.example.yafinance.ui.composable.listItems.TotalItem
import com.example.yafinance.ui.screens.history.composable.DateItem
import com.example.yafinance.ui.utils.formatWithSpaces
import com.example.yafinance.ui.utils.types.DatePickerType
import java.util.Date

@Composable
fun ExpensesHistorySuccess(
    history: List<Expense>,
    onEndDateSelected: (Long?) -> Unit,
    onStartDateSelected: (Long?) -> Unit,
    startDate: Date,
    endDate: Date
) {
    val total by rememberSaveable { mutableStateOf(history.sumOf { it.amount.toInt() }.toString()) }
    var showPicker by remember { mutableStateOf(false) }
    var currentPicking: DatePickerType by remember { mutableStateOf(DatePickerType.START) }

    val formattedTotalAmount = total.formatWithSpaces()
    val trailTotalText = "$formattedTotalAmount ${history.first().currency}"

    Column {
        DateItem(isStart = true, date = startDate, onDateItemClick = {
            currentPicking = DatePickerType.START
            showPicker = true
        })
        DateItem(isStart = false, date = endDate, onDateItemClick = {
            currentPicking = DatePickerType.END
            showPicker = true
        })
        TotalItem(trailText = trailTotalText, hasDivider = false)
        LazyColumn {
            items(items = history, key = { it.id }) { historyItem ->
                ExpenseHistoryItem(historyItem = historyItem)
            }
        }
    }

    if (showPicker) {
        CustomDatePicker(
            selectedDate = when (currentPicking) {
                DatePickerType.START -> startDate.time
                DatePickerType.END -> endDate.time
            },
            onDateSelected = when (currentPicking) {
                DatePickerType.START -> onStartDateSelected
                DatePickerType.END -> onEndDateSelected
            },
            onDismiss = { showPicker = false }
        )
    }
}