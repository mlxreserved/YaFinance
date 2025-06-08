package com.example.yafinance.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.yafinance.ui.navigation.bottomNavBar.BottomNavigationBar
import com.example.yafinance.ui.navigation.host.FinanceNavHost

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
//        topBar = { FinanceTopBar() },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = modifier
    ) { innerPadding ->
        FinanceNavHost(navController = navController, paddingValues = innerPadding)
    }
}