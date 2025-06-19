package com.example.yafinance.ui.screens.expense

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yafinance.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.yafinance.ui.SnackbarViewModel
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.expense.composable.ExpensesSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun ExpensesScreen(
    onTrailIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    expensesViewModel: ExpensesViewModel = hiltViewModel(),
    snackbarViewModel: SnackbarViewModel
) {
    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.expenses_today,
            trailId = R.drawable.ic_history,
            onTrailIconClick = onTrailIconClick
        )
    )

    val expensesState by expensesViewModel.screenState.collectAsStateWithLifecycle()

    when (val state = expensesState) {
        ScreenState.Empty -> {
            EmptyScreen(
                text = stringResource(R.string.empty_expenses),
                onClick = {},
                addText = stringResource(R.string.create_first_expense)
            )
        }

        is ScreenState.Error -> {
            ErrorScreen(screenTitleId = R.string.expenses_today, text = state.message)
            snackbarViewModel.showMessage(state.message)
        }

        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.expenses_today)
        }

        is ScreenState.Success -> {
            ExpensesSuccess(
                expenses = state.result,
                modifier = modifier
            )
        }
    }
}