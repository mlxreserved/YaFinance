package com.example.yafinance.ui.screens.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.yafinance.R
import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.ui.screens.categories.composable.CategoriesSuccess
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun CategoriesScreen(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    TopAppBarStateProvider.update(TopAppBarState(titleId = R.string.my_categories, trailId = null))

    CategoriesSuccess(categories = categories, modifier = modifier)
}