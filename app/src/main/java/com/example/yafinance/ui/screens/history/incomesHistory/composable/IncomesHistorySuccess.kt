package com.example.yafinance.ui.screens.history.incomesHistory.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.composable.datePicker.CustomDatePicker
import com.example.yafinance.ui.composable.listItems.TotalItem
import com.example.yafinance.ui.screens.history.composable.DateItem
import com.example.yafinance.ui.utils.calculatedSumAsString
import com.example.yafinance.ui.utils.formatWithSpaces
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.types.DatePickerType
import java.util.Date

@Composable
fun IncomesHistorySuccess(
    history: List<Income>,
    onEndDateSelected: (Long?) -> Unit,
    onStartDateSelected: (Long?) -> Unit,
    onLeadIconClick: () -> Unit,
    startDate: Date,
    endDate: Date,
    modifier: Modifier = Modifier
) {
    val topAppBarViewModel = LocalTopAppBarViewModel.current

    val total by rememberSaveable {
        mutableStateOf(
            history.calculatedSumAsString { it.amount.toDouble() }
        )
    }
    var showPicker by remember { mutableStateOf(false) }
    var currentPicking: DatePickerType by remember { mutableStateOf(DatePickerType.START) }

    val formattedTotalAmount = total.formatWithSpaces()
    val trailTotalText = "$formattedTotalAmount ${history.first().currency}"

    LaunchedEffect(Unit) {
        topAppBarViewModel.update(
            TopAppBarState(
                titleId = R.string.my_history,
                leadId = R.drawable.ic_back,
                trailId = R.drawable.ic_analys,
                onLeadIconClick = onLeadIconClick
            )
        )
    }

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
                IncomeHistoryItem(
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