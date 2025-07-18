package com.example.ui.components.timePicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.components.datePicker.PickerButton
import com.example.ui.data.ButtonType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerButtons(
    onTimeSelected: (Int?, Int?) -> Unit,
    onDismiss: () -> Unit,
    timePickerState: TimePickerState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            TimePickerButton(
                buttonType = ButtonType.CANCEL,
                onDismiss = onDismiss
            )

            TimePickerButton(
                buttonType = ButtonType.OK,
                onDismiss = onDismiss,
                onTimeSelected = onTimeSelected,
                timePickerState = timePickerState
            )
        }
    }
}