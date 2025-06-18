package com.example.yafinance.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.yafinance.R
import com.example.yafinance.ui.screens.history.composable.DateItem
import com.example.yafinance.ui.screens.history.composable.SumItem
import com.example.yafinance.ui.utils.HistoryType
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider
import java.util.Calendar
import java.util.Date

@Composable
fun HistoryScreen(
    historyType: HistoryType,
    historyViewModel: HistoryViewModel = hiltViewModel()
) {

    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.my_history,
            leadId = R.drawable.ic_back,
            trailId = R.drawable.ic_analys
        )
    )

    val startDate: Date by remember {
        mutableStateOf(
            Calendar.getInstance().apply {
                set(Calendar.DAY_OF_MONTH, 1)
            }.time
        )
    }
    val endDate: Date by remember { mutableStateOf(Date()) }
    val sum = "100 000 Р"

    Column {
        DateItem(isStart = true, date = startDate)
        DateItem(isStart = false, date = endDate)
        SumItem(sum = sum)
    }

}