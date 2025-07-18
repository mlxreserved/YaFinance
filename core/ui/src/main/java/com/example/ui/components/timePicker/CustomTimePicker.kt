package com.example.ui.components.timePicker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import com.example.ui.data.ButtonType
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePicker(
    selectedTime: Long,
    onTimeSelected: (Int?, Int?) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = Calendar.getInstance().apply { timeInMillis = selectedTime }
    val timePickerState = rememberTimePickerState(
        initialHour = calendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = calendar.get(Calendar.MINUTE),
        is24Hour = true
    )

    TimePickerDialog(
        onDismiss = onDismiss,
        onTimeSelected = onTimeSelected,
        timePickerState = timePickerState,
    ) {
        TimePicker(
            state = timePickerState,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onTimeSelected: (Int?, Int?) -> Unit,
    timePickerState: TimePickerState,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TimePickerButton(
                buttonType = ButtonType.CANCEL,
                onDismiss = onDismiss
            )
        },
        confirmButton = {
            TimePickerButton(
                buttonType = ButtonType.OK,
                onDismiss = onDismiss,
                onTimeSelected = onTimeSelected,
                timePickerState = timePickerState
            )
        },
        text = { content() }
    )
}