package com.example.expense.internal.ui.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.expense.R
import com.example.expense.internal.ui.expense.composable.ExpensesSuccess
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
internal fun ExpensesScreen(
    isConnected: Boolean,
    onHistoryClick: () -> Unit,
    onAddTransactionClick: () -> Unit,
    onEditTransactionClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    expensesViewModel: ExpensesViewModel = viewModel(factory = viewModelFactory)
) {
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    val expensesState by expensesViewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        expensesViewModel.getTodayExpenses()
    }

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    trailIconId = R.drawable.ic_history,
                    titleId = R.string.expenses_today,
                    onTrailIconClick = onHistoryClick
                )
                NetworkStatusBanner(
                    isConnected = isConnected,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = YaFinanceTheme.colors.primaryBackground)
                )
            }
        }
    ) { innerPadding ->
        Box (modifier = Modifier.fillMaxSize()) {
            when (val state = expensesState) {
                ScreenState.Empty -> {
                    EmptyScreen(
                        text = stringResource(R.string.empty_expenses),
                        onClick = onAddTransactionClick,
                        addText = stringResource(R.string.create_first_expense),
                        modifier = Modifier
                            .fillMaxSize()
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
                            expensesViewModel.onRetryClicked()
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding())
                    )
                }

                ScreenState.Loading -> {
                    LoadingScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding())
                    )
                }

                is ScreenState.Success -> {
                    ExpensesSuccess(
                        onEditTransactionClick = onEditTransactionClick,
                        expenses = state.result,
                        modifier = Modifier
                            .fillMaxSize()
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