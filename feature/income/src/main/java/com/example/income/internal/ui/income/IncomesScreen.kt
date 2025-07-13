package com.example.income.internal.ui.income

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.income.R
import com.example.income.internal.ui.income.composable.IncomeSuccess
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.components.floatingButton.CustomFloatingButton
import com.example.ui.components.screens.EmptyScreen
import com.example.ui.components.screens.ErrorScreen
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner
import com.example.ui.data.state.ScreenState
import com.example.ui.extensions.toUserMessage

@Composable
internal fun IncomesScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onAddTransactionClick: () -> Unit,
    onEditTransactionClick: (Int) -> Unit,
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
                    isConnected = isConnected,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = YaFinanceTheme.colors.primaryBackground)
                )
            }
        },

    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            when (val state = incomeState) {
                ScreenState.Empty -> {
                    EmptyScreen(
                        text = stringResource(R.string.empty_incomes),
                        onClick = onAddTransactionClick,
                        addText = stringResource(R.string.create_first_income),
                        modifier = Modifier.fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding())
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
                        modifier = Modifier.fillMaxSize()
                    )


                }

                ScreenState.Loading -> {
                    LoadingScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is ScreenState.Success -> {
                    IncomeSuccess(
                        onEditTransactionClick = onEditTransactionClick,
                        incomes = state.result,
                        modifier = Modifier.fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding())
                    )
                }
            }
            CustomFloatingButton(
                onClick = onAddTransactionClick,
                modifier = Modifier.align(Alignment.BottomEnd).padding(end = 16.dp, bottom = 16.dp)
            )
        }
    }
}