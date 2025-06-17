package com.example.yafinance.ui.screens.income

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yafinance.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.income.composable.IncomeSuccess
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun IncomesScreen(
    onTrailIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    incomesViewModel: IncomesViewModel = hiltViewModel()
) {

    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.incomes_today,
            trailId = R.drawable.ic_history,
            onTrailIconClick = onTrailIconClick
        )
    )

    val incomeState by incomesViewModel.incomesState.collectAsStateWithLifecycle()

    when (val state = incomeState) {
        ScreenState.Empty -> {
            EmptyScreen(
                text = stringResource(R.string.empty_incomes),
                underTextAction = {
                    Text(
                        text = stringResource(R.string.create_first_income),
                        textDecoration = TextDecoration.Underline,
                        style = YaFinanceTheme.typography.title,
                        color = YaFinanceTheme.colors.clickableText,
                        modifier = Modifier.clickable {}
                    )
                }
            )
        }

        is ScreenState.Error -> {
            ErrorScreen(screenTitleId = R.string.incomes_today, text = state.message)
        }

        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.incomes_today)
        }

        is ScreenState.Success -> {
            IncomeSuccess(incomes = state.result, modifier = Modifier.fillMaxSize())
        }
    }

}