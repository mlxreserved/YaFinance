package com.example.yafinance.ui.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.example.yafinance.ui.MainScreen
import com.example.yafinance.ui.screens.splash.SplashScreen
import androidx.navigation.compose.rememberNavController

@Composable
fun FinanceNavGraph(
    globalViewModelFactory: ViewModelProvider.Factory,
    expenseViewModelFactory: ViewModelProvider.Factory,
    accountViewModelFactory: ViewModelProvider.Factory,
    incomeViewModelFactory: ViewModelProvider.Factory,
    categoryViewModelFactory: ViewModelProvider.Factory,
    editTransactionViewModelFactory: ViewModelProvider.Factory
) {
    val navController = rememberNavController()
    var splashFinished by rememberSaveable { mutableStateOf(false) }

    Crossfade(targetState = splashFinished) { finished ->
        if (!finished) {
            SplashScreen(onSplashFinished = { splashFinished = true })
        } else {
            MainScreen(
                navController = navController,
                expenseViewModelFactory = expenseViewModelFactory,
                accountViewModelFactory = accountViewModelFactory,
                globalViewModelFactory = globalViewModelFactory,
                incomeViewModelFactory = incomeViewModelFactory,
                categoryViewModelFactory = categoryViewModelFactory,
                editTransactionViewModelFactory = editTransactionViewModelFactory
            )
        }
    }
}