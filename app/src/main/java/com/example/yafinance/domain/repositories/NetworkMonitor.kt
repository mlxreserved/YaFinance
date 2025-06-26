package com.example.yafinance.domain.repositories

import kotlinx.coroutines.flow.StateFlow


/** Проверка подклчюния пользователя к сети **/
interface NetworkMonitor {

    /** Состояние показывающее есть ли у пользователя подключение к интернету **/
    val isConnected: StateFlow<Boolean>

    /** Установка callback для мониторинга состояния сети **/
    fun registerCallback()

    /** Отмена callback для мониторинга состояния сети **/
    fun unregisterCallback()

}