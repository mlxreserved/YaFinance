package com.example.expense.internal.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expense.R
import com.example.expense.internal.ui.composable.ExpensesSuccess
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.components.screens.EmptyScreen
import com.example.ui.components.screens.ErrorScreen
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.data.state.ScreenState
import com.example.ui.extensions.toUserMessage

@Composable
internal fun ExpensesScreen(
    onTrailIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    expensesViewModel: ExpensesViewModel = viewModel(factory = viewModelFactory)
) {
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    val expensesState by expensesViewModel.screenState.collectAsStateWithLifecycle()

    when (val state = expensesState) {
        ScreenState.Empty -> {
            EmptyScreen(
                text = stringResource(R.string.empty_expenses),
                onClick = {},
                addText = stringResource(R.string.create_first_expense),
                modifier = Modifier.fillMaxSize()
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
                modifier = Modifier.fillMaxSize()
            )
        }

        ScreenState.Loading -> {
            LoadingScreen(
                modifier = Modifier.fillMaxSize()
            )
        }

        is ScreenState.Success -> {
            ExpensesSuccess(
                expenses = state.result,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}