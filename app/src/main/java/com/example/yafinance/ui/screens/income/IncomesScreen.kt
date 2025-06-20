package com.example.yafinance.ui.screens.income

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yafinance.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.yafinance.ui.SnackbarViewModel
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.income.composable.IncomeSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider
import com.example.yafinance.ui.utils.toUserMessage

@Composable
fun IncomesScreen(
    snackbarViewModel: SnackbarViewModel,
    onTrailIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    incomesViewModel: IncomesViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.incomes_today,
            trailId = R.drawable.ic_history,
            onTrailIconClick = onTrailIconClick
        )
    )

    val incomeState by incomesViewModel.screenState.collectAsStateWithLifecycle()

    when (val state = incomeState) {
        ScreenState.Empty -> {
            EmptyScreen(
                text = stringResource(R.string.empty_incomes),
                onClick = {},
                addText = stringResource(R.string.create_first_income)
            )
        }

        is ScreenState.Error -> {
            if(state.count > 0){
                snackbarViewModel.showMessage(state.message.toUserMessage(context))
            }
            ErrorScreen(
                screenTitleId = R.string.incomes_today,
                text = state.message.toUserMessage(context),
                onClick = {
                    incomesViewModel.onRetryClicked()
                }
            )


        }

        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.incomes_today)
        }

        is ScreenState.Success -> {
            IncomeSuccess(incomes = state.result, modifier = Modifier.fillMaxSize())
        }
    }

}