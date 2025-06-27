package com.example.yafinance.ui.utils.state

import com.example.yafinance.domain.utils.ErrorModel

/** Состояние экрана при получении данных **/
sealed interface ScreenState<out T> {

    /** При успешном получении данных они записываются в Success **/
    data class Success<T>(val result: T) : ScreenState<T>

    /**
     * При возникновении ошибки, причина ошибка записывается в message.
     *
     * isRetried показывает была ли совершена попытка повторного получения данных.
     */
    data class Error(val message: ErrorModel, val isRetried: Boolean = false) : ScreenState<Nothing>

    /** При загрузке данных **/
    object Loading : ScreenState<Nothing>

    /** Если данные загрузились успешно, но список оказался пустым **/
    object Empty : ScreenState<Nothing>
}
