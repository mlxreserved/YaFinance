package com.example.ui.components.timePicker

import androidx.compose.material3.TimePickerState
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
fun TimePickerButton(
    buttonType: ButtonType,
    onDismiss: () -> Unit,
    onTimeSelected: ((Int?, Int?) -> Unit)? = null,
    timePickerState: TimePickerState? = null
) {
    TextButton(
        onClick = {
            if (onTimeSelected != null) {
                when (buttonType) {
                    ButtonType.CLEAR -> onTimeSelected(null, null)
                    ButtonType.OK -> onTimeSelected(timePickerState?.hour, timePickerState?.minute)
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