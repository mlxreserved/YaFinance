package com.example.yafinance.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.yafinance.ui.composable.floatingButton.CustomFloatingButton
import com.example.yafinance.ui.composable.snackbar.CustomSnackbarHost
import com.example.yafinance.ui.composable.topAppBar.NetworkStatusBanner
import com.example.yafinance.ui.navigation.bottomNavBar.BottomNavigationBar
import com.example.yafinance.ui.navigation.host.FinanceNavHost
import com.example.yafinance.ui.navigation.routes.ScreensRoute.IncomesRoute
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesRoute
//import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.di.module.ViewModelFactory
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.networkMonitor.NetworkStatusViewModel
import com.example.ui.snackbar.SnackbarViewModel
//import com.example.yafinance.ui.viewModel.NetworkStatusViewModel
//import com.example.yafinance.ui.viewModel.SnackbarViewModel
//import com.example.yafinance.ui.viewModel.TopAppBarViewModel

@Composable
fun MainScreen(
    globalViewModelFactory: ViewModelProvider.Factory,
    expenseViewModelFactory: ViewModelProvider.Factory,
    incomeViewModelFactory: ViewModelProvider.Factory,
    accountViewModelFactory: ViewModelProvider.Factory,
    categoryViewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val snackbarViewModel: SnackbarViewModel = viewModel(factory = globalViewModelFactory)
    val networkStatusViewModel: NetworkStatusViewModel = viewModel(factory = globalViewModelFactory)
//    val topAppBarViewModel: TopAppBarViewModel = viewModel(factory = viewModelFactory)

    val snackbarHostState = remember { SnackbarHostState() }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val snackbarMessage by snackbarViewModel.snackbarMessage.collectAsStateWithLifecycle()
    val isConnected by networkStatusViewModel.isConnected.collectAsStateWithLifecycle()
//    val topAppBarState by topAppBarViewModel.topAppBarState.collectAsStateWithLifecycle()

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short
            )
            snackbarViewModel.clearMessage()
        }
    }

    CompositionLocalProvider(
//        LocalTopAppBarViewModel provides topAppBarViewModel,
        LocalSnackbarViewModel provides snackbarViewModel
    ) {
        Scaffold(
            snackbarHost = {
                CustomSnackbarHost(hostState = snackbarHostState) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                }
            },
//            topBar = {
//                Column {
//                    NetworkStatusBanner(
//                        isConnected = true /*isConnected*/,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(color = YaFinanceTheme.colors.primaryBackground)
//                    )
//                }
//            },
            bottomBar = {
                BottomNavigationBar(navController)
            },
            floatingActionButton = {
                when (currentRoute) {
                    ExpensesRoute.javaClass.canonicalName -> CustomFloatingButton { }

                    IncomesRoute.javaClass.canonicalName -> CustomFloatingButton { }
                }
            },
            modifier = modifier
        ) { innerPadding ->
            FinanceNavHost(
                navController = navController,
                isConnected = isConnected,
                expenseViewModelFactory = expenseViewModelFactory,
                incomeViewModelFactory = incomeViewModelFactory,
                accountViewModelFactory = accountViewModelFactory,
                categoryViewModelFactory = categoryViewModelFactory,
                paddingValues = innerPadding
            )
        }
    }
}