package com.example.yafinance.ui.screens.history.incomesHistory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yafinance.R
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.ui.LocalSnackbarViewModel
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.history.incomesHistory.composable.IncomesHistoryEmptyScreen
import com.example.yafinance.ui.screens.history.incomesHistory.composable.IncomesHistorySuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.toUserMessage

@Composable
fun IncomesHistoryScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit,
    historyViewModel: IncomesHistoryViewModel = viewModel(factory = viewModelFactory)
) {
    val context = LocalContext.current
    val topAppBarViewModel = LocalTopAppBarViewModel.current
    val snackbarViewModel = LocalSnackbarViewModel.current

    val historyState by historyViewModel.screenState.collectAsStateWithLifecycle()
    val startDate by historyViewModel.selectedStartDate.collectAsStateWithLifecycle()
    val endDate by historyViewModel.selectedEndDate.collectAsStateWithLifecycle()

    topAppBarViewModel.update(
        TopAppBarState(
            titleId = R.string.my_history,
            leadId = R.drawable.ic_back,
            trailId = R.drawable.ic_analys,
            onLeadIconClick = onLeadIconClick
        )
    )

    when (val state = historyState) {
        ScreenState.Empty -> {
            IncomesHistoryEmptyScreen(
                startDate = startDate,
                endDate = endDate,
                onStartDateSelected = { time -> historyViewModel.updateStartDate(time) },
                onEndDateSelected = { time -> historyViewModel.updateEndDate(time) }
            )
        }

        is ScreenState.Error -> {
            if (state.isRetried) {
                snackbarViewModel.showMessage(state.message.toUserMessage(context))
            }
            ErrorScreen(
                screenTitleId = R.string.my_history,
                text = state.message.toUserMessage(context),
                onClick = {
                    historyViewModel.onRetryClicked()
                },
                onLeadIconClick = onLeadIconClick,
                leadId = R.drawable.ic_back
            )
        }

        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.my_history)
        }

        is ScreenState.Success<List<Income>> -> {
            IncomesHistorySuccess(
                history = state.result,
                startDate = startDate,
                endDate = endDate,
                onStartDateSelected = { time -> historyViewModel.updateStartDate(time) },
                onEndDateSelected = { time -> historyViewModel.updateEndDate(time) }
            )
        }
    }
}