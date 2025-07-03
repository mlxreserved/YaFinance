package com.example.yafinance.ui.screens.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yafinance.R
import com.example.yafinance.ui.LocalSnackbarViewModel
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.categories.composable.CategoriesSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.toUserMessage

@Composable
fun CategoriesScreen(
    viewModelFactory: ViewModelProvider.Factory,
    modifier: Modifier = Modifier,
    categoriesViewModel: CategoriesViewModel = viewModel(factory = viewModelFactory)
) {
    val context = LocalContext.current
    val topAppBarViewModel = LocalTopAppBarViewModel.current
    val snackbarViewModel = LocalSnackbarViewModel.current

    LaunchedEffect(Unit) {
        topAppBarViewModel.update(TopAppBarState(titleId = R.string.my_categories))
    }

    val categoriesState by categoriesViewModel.screenState.collectAsStateWithLifecycle()
    val searchQuery by categoriesViewModel.searchQuery.collectAsStateWithLifecycle()

    when (val state = categoriesState) {
        ScreenState.Empty -> {
            EmptyScreen(
                text = stringResource(R.string.empty_categories),
                screenTitleId = R.string.my_categories
            )
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
            CategoriesSuccess(
                categories = state.result,
                onSearchChanged = { newSearchQuery ->
                    categoriesViewModel.updateSearchQuery(newSearchQuery)
                    categoriesViewModel.findCategories()
                },
                searchQuery = searchQuery,
                modifier = modifier
            )
        }
    }
}