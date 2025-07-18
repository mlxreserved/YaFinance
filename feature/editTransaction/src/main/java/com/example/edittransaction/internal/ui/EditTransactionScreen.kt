package com.example.edittransaction.internal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.income.IncomeDetailed
import com.example.edittransaction.R
import com.example.edittransaction.internal.ui.composable.EditExpenseSuccess
import com.example.edittransaction.internal.ui.composable.EditIncomeSuccess
import com.example.edittransaction.internal.ui.composable.EditTransactionEmpty
import com.example.ui.components.screens.ErrorScreen
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner
import com.example.ui.data.state.ScreenState

@Composable
fun EditTransactionScreen(
    isConnected: Boolean,
    id: Int?,
    isIncome: Boolean,
    isAdd: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onTrailIconClick: () -> Unit,
    onLeadIconClick: () -> Unit,
    editTransactionViewModel: EditTransactionViewModel = viewModel(factory = viewModelFactory)
) {
    val updateState by editTransactionViewModel.updateState.collectAsStateWithLifecycle()
    val deleteState by editTransactionViewModel.deleteState.collectAsStateWithLifecycle()

    if (isIncome) {
        LaunchedEffect(Unit) {
            editTransactionViewModel.onIncomeEnter(id, isAdd)
        }
        val incomeState by editTransactionViewModel.incomeState.collectAsStateWithLifecycle()
        val startDate by editTransactionViewModel.selectedStartDate.collectAsStateWithLifecycle()
        val categoriesState by editTransactionViewModel.categoryState.collectAsStateWithLifecycle()

        when (val state = incomeState) {

            ScreenState.Empty -> {
                EditTransactionEmpty(
                    startTime = startDate,
                    startDate = startDate,
                    onStartDateSelected = { time ->
                        editTransactionViewModel.updateStartDate(time)
                    },
                    onCategoryClick = {
                        editTransactionViewModel.onCategoryClick(isIncome = true)
                    },
                    categoriesState = categoriesState,
                    onTrailIconClick = onTrailIconClick,
                    onStartTimeSelected = { hours, minutes ->
                        editTransactionViewModel.updateStartDate(
                            hours = hours,
                            minutes = minutes
                        )
                    },
                    updateState = updateState,
                    isConnected = isConnected,
                    isIncome = isIncome,
                    editTransactionViewModel = editTransactionViewModel,
                    onLeadIconClick = onLeadIconClick,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            is ScreenState.Error -> {
                Scaffold(
                    topBar = {
                        Column {
                            CustomTopAppBar(
                                leadIconId = R.drawable.ic_back,
                                titleId = R.string.my_incomes,
                                onLeadIconClick = onLeadIconClick,
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
                    val padding = innerPadding
                    ErrorScreen(
                        text = stringResource(R.string.error_edit_loading),
                        onClick = {
                            editTransactionViewModel.onIncomeTransactionRetry(
                                id = id,
                                isAdd = isAdd
                            )
                        }
                    )
                }
            }

            ScreenState.Loading -> {
                Scaffold(
                    topBar = {
                        Column {
                            CustomTopAppBar(
                                leadIconId = R.drawable.ic_back,
                                titleId = R.string.my_incomes,
                                onLeadIconClick = onLeadIconClick,
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
                    val padding = innerPadding
                    LoadingScreen()
                }
            }

            is ScreenState.Success<IncomeDetailed> -> {

                EditIncomeSuccess(
                    income = state.result,
                    startTime = startDate,
                    startDate = startDate,
                    onStartDateSelected = {},
                    onCategoryClick = {
                        editTransactionViewModel.onCategoryClick(isIncome = true)
                    },
                    categoriesState = categoriesState,
                    onTrailIconClick = onTrailIconClick,
                    onSaveUpdate = { income ->
                        editTransactionViewModel.updateIncome(income = income)
                    },
                    onStartTimeSelected = { hours, minutes ->
                        editTransactionViewModel.updateStartDate(
                            hours = hours,
                            minutes = minutes
                        )
                    },
                    onClick = {
                        editTransactionViewModel.deleteIncome(localId = state.result.localId, serverId = state.result.serverId)
                    },
                    deleteState = deleteState,
                    updateState = updateState,
                    isConnected = isConnected,
                    onLeadIconClick = onLeadIconClick,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    } else {
        LaunchedEffect(Unit) {
            editTransactionViewModel.onExpenseEnter(id, isAdd)
        }

        val expenseState by editTransactionViewModel.expenseState.collectAsStateWithLifecycle()
        val startDate by editTransactionViewModel.selectedStartDate.collectAsStateWithLifecycle()
        val categoriesState by editTransactionViewModel.categoryState.collectAsStateWithLifecycle()
        when (val state = expenseState) {
            ScreenState.Empty -> {
                EditTransactionEmpty(
                    startTime = startDate,
                    startDate = startDate,
                    onStartDateSelected = { time ->
                        editTransactionViewModel.updateStartDate(time)
                    },
                    onStartTimeSelected = { hours, minutes ->
                        editTransactionViewModel.updateStartDate(
                            hours = hours,
                            minutes = minutes
                        )
                    },
                    onCategoryClick = {
                        editTransactionViewModel.onCategoryClick(isIncome = false)
                    },
                    categoriesState = categoriesState,
                    onTrailIconClick = onTrailIconClick,
                    updateState = updateState,
                    isConnected = isConnected,
                    isIncome = isIncome,
                    editTransactionViewModel = editTransactionViewModel,
                    onLeadIconClick = onLeadIconClick,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            is ScreenState.Error -> {
                Scaffold(
                    topBar = {
                        Column {
                            CustomTopAppBar(
                                leadIconId = R.drawable.ic_back,
                                titleId = R.string.my_expenses,
                                onLeadIconClick = onLeadIconClick,
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
                    val padding = innerPadding
                    ErrorScreen(
                        text = stringResource(R.string.error_edit_loading),
                        onClick = {
                            editTransactionViewModel.onExpenseTransactionRetry(
                                id = id,
                                isAdd = isAdd
                            )
                        }
                    )
                }
            }

            ScreenState.Loading -> {
                Scaffold(
                    topBar = {
                        Column {
                            CustomTopAppBar(
                                leadIconId = R.drawable.ic_back,
                                titleId = R.string.my_expenses,
                                onLeadIconClick = onLeadIconClick,
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
                    val padding = innerPadding
                    LoadingScreen()
                }
            }

            is ScreenState.Success<ExpenseDetailed> -> {
                EditExpenseSuccess(
                    expense = state.result,
                    startTime = startDate,
                    startDate = startDate,
                    onStartDateSelected = { time ->
                        editTransactionViewModel.updateStartDate(time)
                    },
                    onStartTimeSelected = { hours, minutes ->
                        editTransactionViewModel.updateStartDate(
                            hours = hours,
                            minutes = minutes
                        )
                    },
                    onCategoryClick = {
                        editTransactionViewModel.onCategoryClick(false)
                    },
                    categoriesState = categoriesState,
                    onTrailIconClick = onTrailIconClick,
                    onSaveUpdate = { expense ->
                        editTransactionViewModel.updateExpense(expense = expense)
                    },
                    onClick = {
                        editTransactionViewModel.deleteExpense(localId = state.result.localId, serverId = state.result.serverId)
                    },
                    updateState = updateState,
                    deleteState = deleteState,
                    isConnected = isConnected,
                    onLeadIconClick = onLeadIconClick,
                    modifier = Modifier
                        .fillMaxSize()
                )

            }
        }
    }
}
