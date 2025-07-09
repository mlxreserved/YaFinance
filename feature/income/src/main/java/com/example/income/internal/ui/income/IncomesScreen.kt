package com.example.income.internal.ui.income

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.income.R
import com.example.income.internal.ui.income.composable.IncomeSuccess
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.components.screens.EmptyScreen
import com.example.ui.components.screens.ErrorScreen
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner
import com.example.ui.data.state.ScreenState
import com.example.ui.extensions.toUserMessage

@Composable
fun IncomesScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onHistoryClick: () -> Unit,
    modifier: Modifier = Modifier,
    incomesViewModel: IncomesViewModel = viewModel(factory = viewModelFactory)
) {
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    val incomeState by incomesViewModel.screenState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    trailIconId = R.drawable.ic_history,
                    titleId = R.string.incomes_today,
                    onTrailIconClick = onHistoryClick
                )
                NetworkStatusBanner(
                    isConnected = true /*isConnected*/,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = YaFinanceTheme.colors.primaryBackground)
                )
            }
        }
    ) { innerPadding ->
        when (val state = incomeState) {
            ScreenState.Empty -> {
                EmptyScreen(
                    text = stringResource(R.string.empty_incomes),
                    onClick = {},
                    addText = stringResource(R.string.create_first_income),
                    modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding())
                )
            }

            is ScreenState.Error -> {
                if (state.isRetried) {
                    snackbarViewModel.showMessage(state.message.toUserMessage(context))
                }
                ErrorScreen(
                    text = state.message.toUserMessage(context),
                    onClick = {
                        incomesViewModel.onRetryClicked()
                    },
                    modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding())
                )


            }

            ScreenState.Loading -> {
                LoadingScreen(
                    modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding())
                )
            }

            is ScreenState.Success -> {
                IncomeSuccess(
                    incomes = state.result,
                    modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding())
                )
            }
        }
    }
}