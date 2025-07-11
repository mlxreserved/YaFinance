package com.example.expense.internal.ui.expensesHistory.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.expense.R
import com.example.ui.components.datePicker.CustomDatePicker
import com.example.ui.data.DatePickerType
import com.example.ui.dateItem.DateItem
import java.util.Date

@Composable
internal fun ExpensesHistoryEmptyScreen(
    onEndDateSelected: (Long?) -> Unit,
    onStartDateSelected: (Long?) -> Unit,
    startDate: Date,
    endDate: Date,
    modifier: Modifier = Modifier
) {
    var showPicker by remember { mutableStateOf(false) }
    var currentPicking: DatePickerType by remember { mutableStateOf(DatePickerType.START) }

    Column(
        modifier = modifier
    ) {
        DateItem(isStart = true, date = startDate, onDateItemClick = {
            currentPicking = DatePickerType.START
            showPicker = true
        })
        DateItem(isStart = false, date = endDate, onDateItemClick = {
            currentPicking = DatePickerType.END
            showPicker = true
        })
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.empty_expenses_history),
                style = YaFinanceTheme.typography.title
            )
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