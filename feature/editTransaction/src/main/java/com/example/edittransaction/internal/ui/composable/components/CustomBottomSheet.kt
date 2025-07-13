package com.example.edittransaction.internal.ui.composable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.category.Category
import com.example.ui.components.listItems.customListItem.CustomListItem
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.data.state.ScreenState
import com.example.utils.extensions.string.isEmoji

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CustomBottomSheet(
    onDismiss: () -> Unit,
    onSelectCategory: (Category) -> Unit,
    categoriesState: ScreenState<List<Category>>,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        containerColor = YaFinanceTheme.colors.white,
        onDismissRequest = onDismiss,
        modifier = modifier
    ) {
        when(val state = categoriesState){
            ScreenState.Empty -> TODO()
            is ScreenState.Error -> TODO()
            ScreenState.Loading ->
                LoadingScreen()
            is ScreenState.Success<List<Category>> -> {
                LazyColumn {
                    items(items = state.result, key = { it.id }) { category ->
                        CategoryItem(
                            category,
                            modifier = Modifier.height(70.dp)
                                .clickable{
                                    onSelectCategory(category)
                                    onDismiss()
                                }
                        )
                    }
                }
            }
        }

    }
}

@Composable
internal fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier
) {
    CustomListItem(
        leadIcon = {
            Text(
                text = category.leadIcon,
                style = if (category.leadIcon.isEmoji()) YaFinanceTheme.typography.emoji else YaFinanceTheme.typography.emojiText
            )
        },
        title = {
            Text(
                style = YaFinanceTheme.typography.title,
                text = category.title
            )
        },
        modifier = modifier
    )
}