package com.example.yafinance.ui.screens.categories.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            searchQuery = searchQuery,
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        )

        LazyColumn {
            items(items = categories, key = { it.id }) { category ->
                CategoryItem(
                    category,
                    modifier = Modifier.height(70.dp)
                )
            }
        }
    }
}