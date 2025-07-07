//package com.example.yafinance.ui.screens.expense
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import com.example.yafinance.R
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.ui.LocalSnackbarViewModel
////import com.example.yafinance.ui.LocalSnackbarViewModel
//import com.example.yafinance.ui.composable.screens.EmptyScreen
//import com.example.yafinance.ui.composable.screens.ErrorScreen
//import com.example.yafinance.ui.composable.screens.LoadingScreen
//import com.example.yafinance.ui.screens.expense.composable.ExpensesSuccess
//import com.example.yafinance.ui.utils.state.ScreenState
//import com.example.yafinance.ui.utils.toUserMessage
//
//@Composable
//fun ExpensesScreen(
//    onTrailIconClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    viewModelFactory: ViewModelProvider.Factory,
//    expensesViewModel: ExpensesViewModel = viewModel(factory = viewModelFactory)
//) {
//    val snackbarViewModel = LocalSnackbarViewModel.current
//    val context = LocalContext.current
//
//    val expensesState by expensesViewModel.screenState.collectAsStateWithLifecycle()
//
//    when (val state = expensesState) {
//        ScreenState.Empty -> {
//            EmptyScreen(
//                text = stringResource(R.string.empty_expenses),
//                onClick = {},
//                addText = stringResource(R.string.create_first_expense),
//                screenTitleId = R.string.expenses_today,
//                trailId = R.drawable.ic_history,
//                onTrailIconClick = onTrailIconClick,
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//
//        is ScreenState.Error -> {
//            if (state.isRetried) {
//                snackbarViewModel.showMessage(state.message.toUserMessage(context))
//            }
//            ErrorScreen(
//                screenTitleId = R.string.expenses_today,
//                text = state.message.toUserMessage(context),
//                onClick = {
//                    expensesViewModel.onRetryClicked()
//                },
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//
//        ScreenState.Loading -> {
//            LoadingScreen(
//                screenTitleId = R.string.expenses_today,
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//
//        is ScreenState.Success -> {
//            ExpensesSuccess(
//                expenses = state.result,
//                onTrailIconClick = onTrailIconClick,
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}