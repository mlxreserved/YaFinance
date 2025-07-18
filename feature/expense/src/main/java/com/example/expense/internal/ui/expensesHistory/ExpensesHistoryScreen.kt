package com.example.expense.internal.ui.expensesHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.expense.Expense
import com.example.expense.R
import com.example.expense.internal.ui.expensesHistory.composable.ExpensesHistoryEmptyScreen
import com.example.expense.internal.ui.expensesHistory.composable.ExpensesHistorySuccess
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.components.screens.ErrorScreen
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner
import com.example.ui.data.state.ScreenState
import com.example.ui.extensions.toUserMessage

@Composable
internal fun ExpensesHistoryScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onEditTransactionClick: (Int) -> Unit,
    onLeadIconClick: () -> Unit,
    onTrailIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    historyViewModel: ExpensesHistoryViewModel = viewModel(factory = viewModelFactory)
) {
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    val historyState by historyViewModel.screenState.collectAsStateWithLifecycle()
    val startDate by historyViewModel.selectedStartDate.collectAsStateWithLifecycle()
    val endDate by historyViewModel.selectedEndDate.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        historyViewModel.getHistory()
    }

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    trailIconId = R.drawable.ic_analys,
                    titleId = R.string.my_history,
                    leadIconId = R.drawable.ic_back,
                    onLeadIconClick = onLeadIconClick,
                    onTrailIconClick = onTrailIconClick
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
        when (val state = historyState) {
            ScreenState.Empty -> {
                ExpensesHistoryEmptyScreen(
                    startDate = startDate,
                    endDate = endDate,
                    onStartDateSelected = { time -> historyViewModel.updateStartDate(time) },
                    onEndDateSelected = { time -> historyViewModel.updateEndDate(time) },
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
                        historyViewModel.onRetryClicked()
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

            is ScreenState.Success<List<Expense>> -> {
                ExpensesHistorySuccess(
                    history = state.result,
                    startDate = startDate,
                    endDate = endDate,
                    onEditTransactionClick = onEditTransactionClick,
                    onStartDateSelected = { time -> historyViewModel.updateStartDate(time) },
                    onEndDateSelected = { time -> historyViewModel.updateEndDate(time) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                )
            }
        }
    }
}