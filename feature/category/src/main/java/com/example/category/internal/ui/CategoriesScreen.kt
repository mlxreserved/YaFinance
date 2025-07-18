package com.example.category.internal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.category.R
import com.example.category.internal.ui.composable.CategoriesSuccess
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.components.screens.EmptyScreen
import com.example.ui.components.screens.ErrorScreen
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner
import com.example.ui.data.state.ScreenState
import com.example.ui.extensions.toUserMessage

@Composable
internal fun CategoriesScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    modifier: Modifier = Modifier,
    categoriesViewModel: CategoriesViewModel = viewModel(factory = viewModelFactory)
) {
    val context = LocalContext.current
    val snackbarViewModel = LocalSnackbarViewModel.current

    val categoriesState by categoriesViewModel.screenState.collectAsStateWithLifecycle()
    val searchQuery by categoriesViewModel.searchQuery.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    titleId = R.string.my_categories,
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
        when (val state = categoriesState) {
            ScreenState.Empty -> {
                EmptyScreen(
                    text = stringResource(R.string.empty_categories),
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
                        categoriesViewModel.onRetryClicked()
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

            is ScreenState.Success -> {
                CategoriesSuccess(
                    categories = state.result,
                    onSearchChanged = { newSearchQuery ->
                        categoriesViewModel.updateSearchQuery(newSearchQuery)
                        categoriesViewModel.findCategories()
                    },
                    searchQuery = searchQuery,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                )
            }
        }
    }

}