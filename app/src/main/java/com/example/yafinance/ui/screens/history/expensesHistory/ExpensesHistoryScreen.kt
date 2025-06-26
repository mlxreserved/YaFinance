package com.example.yafinance.ui.screens.history.expensesHistory

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yafinance.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.yafinance.domain.models.expense.Expense
import com.example.yafinance.ui.SnackbarViewModel
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.history.expensesHistory.composable.ExpensesHistoryEmptyScreen
import com.example.yafinance.ui.screens.history.expensesHistory.composable.ExpensesHistorySuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider
import com.example.yafinance.ui.utils.toUserMessage

@Composable
fun ExpensesHistoryScreen(
    snackbarViewModel: SnackbarViewModel,
    onLeadIconClick: () -> Unit,
    historyViewModel: ExpensesHistoryViewModel = hiltViewModel<ExpensesHistoryViewModel>()
) {
    val context = LocalContext.current

    val historyState by historyViewModel.screenState.collectAsStateWithLifecycle()
    val startDate by historyViewModel.selectedStartDate.collectAsStateWithLifecycle()
    val endDate by historyViewModel.selectedEndDate.collectAsStateWithLifecycle()

    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.my_history,
            leadId = R.drawable.ic_back,
            trailId = R.drawable.ic_analys,
            onLeadIconClick = onLeadIconClick
        )
    )

    when (val state = historyState) {
        ScreenState.Empty -> {
            ExpensesHistoryEmptyScreen(
                startDate = startDate,
                endDate = endDate,
                onStartDateSelected = { time -> historyViewModel.updateStartDate(time) },
                onEndDateSelected = { time -> historyViewModel.updateEndDate(time) }
            )
        }
        is ScreenState.Error -> {
            if(state.isRetried) {
                snackbarViewModel.showMessage(state.message.toUserMessage(context))
            }

            ErrorScreen(
                screenTitleId = R.string.my_history,
                text = state.message.toUserMessage(context),
                onClick = {
                    historyViewModel.onRetryClicked()
                }
            )
        }
        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.my_history)
        }
        is ScreenState.Success<List<Expense>> -> {
            ExpensesHistorySuccess(
                history = state.result,
                startDate = startDate,
                endDate = endDate,
                onStartDateSelected = { time -> historyViewModel.updateStartDate(time) },
                onEndDateSelected = { time -> historyViewModel.updateEndDate(time) }
            )
        }
    }
}