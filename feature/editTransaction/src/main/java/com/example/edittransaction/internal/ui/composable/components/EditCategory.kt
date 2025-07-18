package com.example.edittransaction.internal.ui.composable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.edittransaction.R
import com.example.ui.components.listItems.customListItem.CustomListItem

@Composable
fun EditCategory(
    currentCategory: String,
    onCategoryClick: () -> Unit
) {
    CustomListItem(
        title = {
            Text(
                text = stringResource(R.string.category),
                style = YaFinanceTheme.typography.title
            )
        },
        trailItem = {
            Icon(
                imageVector = ImageVector.vectorResource(com.example.ui.R.drawable.ic_more_vert),
                contentDescription = null
            )
        },
        trailText = currentCategory,
        modifier = Modifier
            .height(70.dp)
            .clickable {
                onCategoryClick()
            }
    )
}