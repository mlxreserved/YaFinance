package com.example.yafinance.ui.screens.history

import com.example.yafinance.ui.screens.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import java.util.Date

abstract class BaseHistoryViewModel<T> : BaseViewModel<T>() {

    protected abstract fun getHistory(startDate: Date? = null, endDate: Date? = null)

    private val _selectedStartDate = MutableStateFlow(
        Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 23)
        }.time
    )
    val selectedStartDate: StateFlow<Date> = _selectedStartDate.asStateFlow()

    private val _selectedEndDate = MutableStateFlow(Date())
    val selectedEndDate: StateFlow<Date> = _selectedEndDate.asStateFlow()

    fun updateStartDate(newStartDateMillis: Long?) {
        if (newStartDateMillis != null) {
            _selectedStartDate.update { Date(newStartDateMillis) }
        } else {
            _selectedStartDate.update { getCalendarDate() }
        }
        getHistory(startDate = _selectedStartDate.value, endDate = selectedEndDate.value)
    }

    fun updateEndDate(newEndDateMillis: Long?) {
        if(newEndDateMillis != null) {
            _selectedEndDate.update { Date(newEndDateMillis) }
        }
        else {
            _selectedEndDate.update { Date() }
        }
        getHistory(startDate = selectedStartDate.value, endDate = _selectedEndDate.value)
    }

    private fun getCalendarDate(): Date = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 23)

    }.time

}