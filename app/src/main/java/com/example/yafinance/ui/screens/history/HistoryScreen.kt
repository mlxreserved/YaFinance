package com.example.yafinance.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.yafinance.ui.screens.history.composable.DateItem
import com.example.yafinance.ui.screens.history.composable.SumItem

@Composable
fun HistoryScreen() {
    val startDate = "Февраль 2025"
    val endDate = "23:41"
    val sum = "100 000 Р"

    Column {
        DateItem(date = startDate)
        DateItem(date = endDate)
        SumItem(sum = sum)
    }

}