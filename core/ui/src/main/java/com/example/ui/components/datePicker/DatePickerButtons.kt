package com.example.ui.components.datePicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.data.ButtonType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerButtons(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    datePickerState: DatePickerState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PickerButton(
            buttonType = ButtonType.CLEAR,
            onDismiss = onDismiss,
            onDateSelected = onDateSelected
        )

        Row {
            PickerButton(buttonType = ButtonType.CANCEL, onDismiss = onDismiss)

            PickerButton(
                buttonType = ButtonType.OK,
                onDismiss = onDismiss,
                onDateSelected = onDateSelected,
                datePickerState = datePickerState
            )
        }
    }
}