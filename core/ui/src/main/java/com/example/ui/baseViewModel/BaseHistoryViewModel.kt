package com.example.ui.baseViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import java.util.Date

abstract class BaseHistoryViewModel<T> : BaseViewModel<T>() {
    abstract fun getHistory(
        startDate: Date? = null,
        endDate: Date? = null,
        isRetried: Boolean = false
    )

    private val _selectedStartDate = MutableStateFlow(getCalendarDate())
    val selectedStartDate: StateFlow<Date> = _selectedStartDate.asStateFlow()

    private val _selectedEndDate = MutableStateFlow(getEndCalendarDate())
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
        if (newEndDateMillis != null) {
            _selectedEndDate.update {
                Calendar.getInstance().apply {
                    timeInMillis = newEndDateMillis
                    set(Calendar.HOUR_OF_DAY, 23)
                    set(Calendar.MINUTE, 59)
                    set(Calendar.SECOND, 59)
                }.time
            }
        } else {
            _selectedEndDate.update { getEndCalendarDate() }
        }
        getHistory(startDate = selectedStartDate.value, endDate = _selectedEndDate.value)
    }

    private fun getCalendarDate(): Date = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 23)
    }.time

    private fun getEndCalendarDate(): Date = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
    }.time
}