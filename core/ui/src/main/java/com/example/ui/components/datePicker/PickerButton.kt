package com.example.ui.components.datePicker

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.data.ButtonType
import com.example.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickerButton(
    buttonType: ButtonType,
    onDismiss: () -> Unit,
    onDateSelected: ((Long?) -> Unit)? = null,
    datePickerState: DatePickerState? = null
) {
    TextButton(
        onClick = {
            if (onDateSelected != null) {
                when (buttonType) {
                    ButtonType.CLEAR -> onDateSelected(null)
                    ButtonType.OK -> onDateSelected(datePickerState?.selectedDateMillis)
                    ButtonType.CANCEL -> {}
                }
            }
            onDismiss()
        },
    ) {
        when (buttonType) {
            ButtonType.CLEAR -> Text(
                text = stringResource(R.string.clear),
                style = YaFinanceTheme.typography.textButtonReject
            )

            ButtonType.OK -> Text(
                text = stringResource(R.string.ok),
                style = YaFinanceTheme.typography.textButtonConfirm
            )

            ButtonType.CANCEL -> Text(
                text = stringResource(R.string.cancel),
                style = YaFinanceTheme.typography.textButtonReject
            )
        }
    }
}