package com.example.yafinance.ui.screens.income

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yafinance.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.LocalSnackbarViewModel
//import com.example.yafinance.ui.LocalSnackbarViewModel
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.income.composable.IncomeSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.toUserMessage

@Composable
fun IncomesScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onTrailIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    incomesViewModel: IncomesViewModel = viewModel(factory = viewModelFactory)
) {
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    val incomeState by incomesViewModel.screenState.collectAsStateWithLifecycle()

    when (val state = incomeState) {
        ScreenState.Empty -> {
            EmptyScreen(
                text = stringResource(R.string.empty_incomes),
                onClick = {},
                addText = stringResource(R.string.create_first_income),
                screenTitleId = R.string.incomes_today,
                onTrailIconClick = onTrailIconClick,
                trailId = R.drawable.ic_history,
                modifier = Modifier.fillMaxSize()
            )
        }

        is ScreenState.Error -> {
            if (state.isRetried) {
                snackbarViewModel.showMessage(state.message.toUserMessage(context))
            }
            ErrorScreen(
                screenTitleId = R.string.incomes_today,
                text = state.message.toUserMessage(context),
                onClick = {
                    incomesViewModel.onRetryClicked()
                },
                modifier = Modifier.fillMaxSize()
            )


        }

        ScreenState.Loading -> {
            LoadingScreen(
                screenTitleId = R.string.incomes_today,
                modifier = Modifier.fillMaxSize()
            )
        }

        is ScreenState.Success -> {
            IncomeSuccess(
                incomes = state.result,
                modifier = Modifier.fillMaxSize(),
                onTrailIconClick = onTrailIconClick
            )
        }
    }
}