package com.example.yafinance.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.yafinance.ui.composable.floatingButton.CustomFloatingButton
import com.example.yafinance.ui.composable.snackbar.CustomSnackbarHost
import com.example.yafinance.ui.composable.topAppBar.CustomTopAppBar
import com.example.yafinance.ui.navigation.bottomNavBar.BottomNavigationBar
import com.example.yafinance.ui.navigation.host.FinanceNavHost
import com.example.yafinance.ui.navigation.routes.ScreensRoute.IncomesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesRoute

@Composable
fun MainScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    snackbarViewModel: SnackbarViewModel = viewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val currentRoute = navBackStackEntry?.destination?.route
    val snackbarMessage by snackbarViewModel.snackbarMessage.collectAsStateWithLifecycle()

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short
            )
            snackbarViewModel.clearMessage()
        }
    }

    Scaffold(
        snackbarHost = {
            CustomSnackbarHost(hostState = snackbarHostState) {
                snackbarHostState.currentSnackbarData?.dismiss()
            }
        },
        topBar = {
            CustomTopAppBar()
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        floatingActionButton = {
            when (currentRoute) {
                ExpensesRoute.javaClass.canonicalName -> CustomFloatingButton {}

                IncomesRoute.javaClass.canonicalName -> CustomFloatingButton { }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        FinanceNavHost(snackbarViewModel = snackbarViewModel, navController = navController, paddingValues = innerPadding)
    }
}