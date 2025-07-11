package com.example.yafinance.ui.composable.datePicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.example.design.theme.customTheme.YaFinanceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    selectedDate: Long,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedDate)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            DatePickerButtons(
                onDateSelected = onDateSelected,
                onDismiss = onDismiss,
                datePickerState = datePickerState
            )
        },
        colors = DatePickerDefaults.colors(
            containerColor = YaFinanceTheme.colors.secondaryBackground,
            selectedDayContainerColor = YaFinanceTheme.colors.primaryBackground
        )
    ) {
        DatePicker(
            state = datePickerState,
            title = null,
            headline = null,
            showModeToggle = false,
            colors = DatePickerDefaults.colors(
                containerColor = YaFinanceTheme.colors.secondaryBackground,
                selectedDayContainerColor = YaFinanceTheme.colors.primaryBackground,
                selectedYearContainerColor = YaFinanceTheme.colors.primaryBackground,
                selectedYearContentColor = YaFinanceTheme.colors.primaryText,
                todayContentColor = YaFinanceTheme.colors.primaryText,
                todayDateBorderColor = YaFinanceTheme.colors.primaryBackground,
                selectedDayContentColor = YaFinanceTheme.colors.primaryText
            )
        )
    }
}