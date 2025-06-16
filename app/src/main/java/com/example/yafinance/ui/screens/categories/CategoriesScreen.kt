package com.example.yafinance.ui.screens.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.example.yafinance.R
import com.example.yafinance.ui.composable.screens.EmptyScreen
import com.example.yafinance.ui.composable.screens.ErrorScreen
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.categories.composable.CategoriesSuccess
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {
    TopAppBarStateProvider.update(TopAppBarState(titleId = R.string.my_categories, trailId = null))

    val categoriesState by categoriesViewModel.categoriesState.collectAsStateWithLifecycle()

    when(val state = categoriesState){
        ScreenState.Empty -> {
            EmptyScreen("Empty screen")
        }
        is ScreenState.Error -> {
            ErrorScreen(state.message)
        }
        ScreenState.Loading -> {
            LoadingScreen()
        }
        is ScreenState.Success -> {
            CategoriesSuccess(categories = state.result, modifier = modifier)
        }
    }
}