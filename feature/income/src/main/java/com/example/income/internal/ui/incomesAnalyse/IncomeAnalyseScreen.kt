package com.example.income.internal.ui.incomesAnalyse

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
import com.example.income.R
import com.example.income.internal.ui.incomesAnalyse.composable.IncomeAnalyseEmptyScreen
import com.example.income.internal.ui.incomesAnalyse.composable.IncomeAnalyseSuccess
import com.example.model.categoryTotal.CategoryTotal
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.components.screens.ErrorScreen
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner
import com.example.ui.data.state.ScreenState
import com.example.ui.extensions.toUserMessage

@Composable
internal fun IncomeAnalyseScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    analyseViewModel: IncomeAnalyseViewModel = viewModel(factory = viewModelFactory)
) {
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    val historyState by analyseViewModel.screenState.collectAsStateWithLifecycle()
    val startDate by analyseViewModel.selectedStartDate.collectAsStateWithLifecycle()
    val endDate by analyseViewModel.selectedEndDate.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        analyseViewModel.getHistory()
    }

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    titleId = R.string.analyse,
                    leadIconId = R.drawable.ic_back,
                    onLeadIconClick = onLeadIconClick
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
                IncomeAnalyseEmptyScreen(
                    startDate = startDate,
                    endDate = endDate,
                    onStartDateSelected = { time -> analyseViewModel.updateStartDate(time) },
                    onEndDateSelected = { time -> analyseViewModel.updateEndDate(time) },
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
                        analyseViewModel.onRetryClicked()
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

            is ScreenState.Success<List<CategoryTotal>> -> {
                IncomeAnalyseSuccess(
                    history = state.result,
                    startDate = startDate,
                    endDate = endDate,
                    onStartDateSelected = { time -> analyseViewModel.updateStartDate(time) },
                    onEndDateSelected = { time -> analyseViewModel.updateEndDate(time) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                )
            }
        }
    }
}