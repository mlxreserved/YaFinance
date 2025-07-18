package com.example.yafinance.ui.navigation.host

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.account.api.navigation.accountBase
import com.example.account.api.navigation.editAccountScreen
import com.example.account.api.navigation.navigateToEditAccount
import com.example.category.api.navigation.categoryScreen
import com.example.expense.api.navigation.ExpensesAllRoutes
import com.example.expense.api.navigation.expensesBase
import com.example.expense.api.navigation.expensesHistoryScreen
import com.example.expense.api.navigation.navigateToExpensesHistory
import com.example.income.api.navigation.incomesBase
import com.example.income.api.navigation.incomesHistoryBase
import com.example.income.api.navigation.navigateToIncomesHistory
import com.example.edittransaction.api.navigation.editTransactionScreen
import com.example.edittransaction.api.navigation.navigateToEditTransaction
import com.example.expense.api.navigation.expensesAnalyseScreen
import com.example.expense.api.navigation.navigateToExpensesAnalyse
import com.example.income.api.navigation.incomesAnalyseScreen
import com.example.income.api.navigation.navigateToIncomesAnalyse
import com.example.settings.api.navigation.settingsScreen

@Composable
fun FinanceNavHost(
    isConnected: Boolean,
    expenseViewModelFactory: ViewModelProvider.Factory,
    incomeViewModelFactory: ViewModelProvider.Factory,
    accountViewModelFactory: ViewModelProvider.Factory,
    categoryViewModelFactory: ViewModelProvider.Factory,
    editTransactionViewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        exitTransition = {
            fadeOut(tween(0))
        },
        enterTransition = {
            fadeIn(tween(0))
        },
        popExitTransition = {
            fadeOut(tween(0))
        },
        popEnterTransition = {
            fadeIn(tween(0))
        },
        navController = navController,
        startDestination = ExpensesAllRoutes,
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        expensesBase(
            isConnected = isConnected,
            viewModelFactory = expenseViewModelFactory,
            onHistoryIconClick = navController::navigateToExpensesHistory,
            historyDestination = {
                expensesHistoryScreen(
                    isConnected = isConnected,
                    viewModelFactory = expenseViewModelFactory,
                    onLeadIconClick = navController::navigateUp,
                    onEditTransactionClick = { id ->
                        navController.navigateToEditTransaction(
                            isAdd = false,
                            isIncome = false,
                            id = id
                        )
                    },
                    onTrailIconClick = navController::navigateToExpensesAnalyse,
                    editTransactionDestination = {
                        editTransactionScreen(
                            isConnected = isConnected,
                            viewModelFactory = editTransactionViewModelFactory,
                            onLeadIconClick = {
                                navController.navigateUp()
                            },
                            onSuccess = {
                                navController.navigateUp()
                            }
                        )
                    }
                ) {
                    expensesAnalyseScreen(
                        isConnected = isConnected,
                        viewModelFactory = expenseViewModelFactory,
                        onLeadIconClick = {
                            navController.navigateUp()
                        }
                    )
                }
            },
            onAddTransactionClick = {
                navController.navigateToEditTransaction(
                    isAdd = true,
                    isIncome = false,
                )
            },
            onEditTransactionClick = { id ->
                navController.navigateToEditTransaction(
                    isAdd = false,
                    isIncome = false,
                    id = id
                )
            }
        ) {
            editTransactionScreen(
                isConnected = isConnected,
                viewModelFactory = editTransactionViewModelFactory,
                onLeadIconClick = {
                    navController.navigateUp()
                },
                onSuccess = {
                    navController.navigateUp()
                }
            )
        }

        incomesBase(
            isConnected = isConnected,
            viewModelFactory = incomeViewModelFactory,
            onHistoryIconClick = navController::navigateToIncomesHistory,
            historyDestination = {
                incomesHistoryBase(
                    isConnected = isConnected,
                    viewModelFactory = incomeViewModelFactory,
                    onEditTransactionClick = { id ->
                        navController.navigateToEditTransaction(
                            isAdd = false,
                            isIncome = true,
                            id = id
                        )
                    },
                    onLeadIconClick = navController::navigateUp,
                    onTrailIconClick = navController::navigateToIncomesAnalyse,
                    editTransactionDestination = {
                        editTransactionScreen(
                            isConnected = isConnected,
                            viewModelFactory = editTransactionViewModelFactory,
                            onLeadIconClick = {
                                navController.navigateUp()
                            },
                            onSuccess = {
                                navController.navigateUp()
                            }
                        )
                    }
                ) {
                    incomesAnalyseScreen(
                        isConnected = isConnected,
                        viewModelFactory = incomeViewModelFactory,
                        onLeadIconClick = {
                            navController.navigateUp()
                        }
                    )
                }
            },
            onAddTransactionClick = {
                navController.navigateToEditTransaction(
                    isAdd = true,
                    isIncome = true,
                )
            },
            onEditTransactionClick = { id ->
                navController.navigateToEditTransaction(
                    isAdd = false,
                    isIncome = true,
                    id = id
                )
            }
        ) {
            editTransactionScreen(
                isConnected = isConnected,
                viewModelFactory = editTransactionViewModelFactory,
                onLeadIconClick = {
                    navController.navigateUp()
                },
                onSuccess = {
                    navController.navigateUp()
                }
            )
        }

        accountBase(
            isConnected = isConnected,
            viewModelFactory = accountViewModelFactory,
            onEditIconClick = { account ->
                navController.navigateToEditAccount(account)
            }
        ) {
            editAccountScreen(
                isConnected = isConnected,
                onSuccess = {
                    navController.navigateUp()
                },
                onLeadIconClick = {
                    navController.navigateUp()
                },
                viewModelFactory = accountViewModelFactory
            )
        }

        categoryScreen(
            isConnected = isConnected,
            viewModelFactory = categoryViewModelFactory
        )

        settingsScreen(
            isConnected = isConnected
        )


    }
}

