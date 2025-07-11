package com.example.expense.internal.ui.expensesHistory.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.model.expense.Expense
import com.example.ui.components.datePicker.CustomDatePicker
import com.example.ui.components.listItems.TotalItem
import com.example.ui.data.DatePickerType
import com.example.ui.dateItem.DateItem
import com.example.utils.extensions.calculatedSum.calculatedSumAsString
import com.example.utils.extensions.string.formatWithSpaces
import java.util.Date

@Composable
internal fun ExpensesHistorySuccess(
    history: List<Expense>,
    onEndDateSelected: (Long?) -> Unit,
    onStartDateSelected: (Long?) -> Unit,
    startDate: Date,
    endDate: Date,
    modifier: Modifier = Modifier
) {
    val total by rememberSaveable {
        mutableStateOf(
            history.calculatedSumAsString { it.amount.toDouble() }
        )
    }
    var showPicker by remember { mutableStateOf(false) }
    var currentPicking: DatePickerType by remember { mutableStateOf(DatePickerType.START) }

    val formattedTotalAmount = total.formatWithSpaces()
    val trailTotalText = "$formattedTotalAmount ${history.first().currency}"

    Column(
        modifier = modifier
    ) {
        DateItem(
            isStart = true,
            date = startDate,
            onDateItemClick = {
                currentPicking = DatePickerType.START
                showPicker = true
            },
            modifier = Modifier.height(56.dp)
        )
        DateItem(
            isStart = false,
            date = endDate,
            onDateItemClick = {
                currentPicking = DatePickerType.END
                showPicker = true
            },
            modifier = Modifier.height(56.dp)
        )
        TotalItem(
            trailText = trailTotalText,
            hasDivider = false,
            modifier = Modifier.height(56.dp)
        )
        LazyColumn {
            items(items = history, key = { it.id }) { historyItem ->
                ExpenseHistoryItem(
                    historyItem = historyItem,
                    modifier = Modifier.height(72.dp)
                )
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