package com.example.yafinance.ui.screens.categories.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.yafinance.R
import com.example.yafinance.domain.models.category.Category

@Composable
fun CategoriesSuccess(categories: List<Category>, modifier: Modifier = Modifier) {

    val trailIcon = ImageVector.vectorResource(R.drawable.search)

    Column(modifier = modifier) {
        CategorySearch(trailIcon = trailIcon)

        LazyColumn {
            items(items = categories, key = { it.id }) { category ->
                CategoryItem(title = category.title, leadIcon = category.leadIcon)
            }
        }
    }
}