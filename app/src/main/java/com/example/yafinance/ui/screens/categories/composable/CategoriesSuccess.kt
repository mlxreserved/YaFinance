package com.example.yafinance.ui.screens.categories.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.yafinance.domain.models.category.Category

@Composable
fun CategoriesSuccess(
    categories: List<Category>,
    modifier: Modifier = Modifier,
    onSearchChanged: (String) -> Unit,
    searchQuery: String
) {

    Column(modifier = modifier) {
        CategorySearch(
            onSearchChanged = onSearchChanged,
            searchQuery = searchQuery
        )

        LazyColumn {
            items(items = categories, key = { it.id }) { category ->
                CategoryItem(category)
            }
        }
    }
}