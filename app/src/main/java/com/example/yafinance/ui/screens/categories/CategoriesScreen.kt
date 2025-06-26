package com.example.yafinance.ui.screens.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yafinance.R
import com.example.yafinance.ui.SnackbarViewModel
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.categories.composable.CategoriesSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider
import com.example.yafinance.ui.utils.toUserMessage

@Composable
fun CategoriesScreen(
    viewModelFactory: ViewModelProvider.Factory,
    snackbarViewModel: SnackbarViewModel,
    modifier: Modifier = Modifier,
    categoriesViewModel: CategoriesViewModel = viewModel(factory = viewModelFactory)
) {
    val context = LocalContext.current
    TopAppBarStateProvider.update(TopAppBarState(titleId = R.string.my_categories))

    val categoriesState by categoriesViewModel.screenState.collectAsStateWithLifecycle()

    when (val state = categoriesState) {
        ScreenState.Empty -> {
            EmptyScreen("Empty screen")
        }

        is ScreenState.Error -> {
            if (state.isRetried) {
                snackbarViewModel.showMessage(state.message.toUserMessage(context))
            }
            ErrorScreen(
                screenTitleId = R.string.my_categories,
                text = state.message.toUserMessage(context),
                onClick = {
                    categoriesViewModel.onRetryClicked()
                }
            )
        }

        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.my_categories)
        }

        is ScreenState.Success -> {
            CategoriesSuccess(categories = state.result, modifier = modifier)
        }
    }
}