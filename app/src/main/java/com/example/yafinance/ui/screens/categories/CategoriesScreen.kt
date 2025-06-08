package com.example.yafinance.ui.screens.categories

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.yafinance.R
import com.example.yafinance.domain.models.category.Category
import com.example.yafinance.ui.screens.categories.composable.CategoryItem
import com.example.yafinance.ui.screens.categories.composable.CategorySearch

@Composable
fun CategoriesScreen(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {

        item {
            val trailIcon = ImageVector.vectorResource(R.drawable.search)

            CategorySearch(trailIcon = trailIcon)
        }

        items(items = categories) { category ->
            CategoryItem(title = category.title, leadIcon = category.leadIcon)
        }
    }
}