package com.example.edittransaction.internal.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.income.IncomeDetailed
import com.example.edittransaction.R
import com.example.edittransaction.internal.ui.composable.EditBalance
import com.example.edittransaction.internal.ui.composable.EditComment
import com.example.ui.components.listItems.customListItem.CustomListItem
import com.example.ui.data.state.ScreenState
import com.example.ui.dateItem.DateItem
import com.example.utils.extensions.string.toDateWithTimeString

@Composable
fun EditTransactionScreen(
    id: Int,
    isIncome: Boolean,
    isAdd: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    editTransactionViewModel: EditTransactionViewModel = viewModel(factory = viewModelFactory)
) {


    if (isIncome) {
        val incomeState by editTransactionViewModel.incomeState.collectAsStateWithLifecycle()
        when (val state = incomeState) {
            ScreenState.Empty -> TODO()
            is ScreenState.Error -> TODO()
            ScreenState.Loading -> TODO()
            is ScreenState.Success<IncomeDetailed> -> {
                var comment by rememberSaveable { mutableStateOf(state.result.comment) }
                var currentSum by rememberSaveable { mutableStateOf(state.result.sum) }
                var currency by rememberSaveable { mutableStateOf(state.result.currency) }

                CustomListItem(
                    title = {
                        Text(
                            text = stringResource(R.string.account),
                            style = YaFinanceTheme.typography.title
                        )
                    },
                    trailText = state.result.accountName,
                    modifier = Modifier.height(70.dp)
                )
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
                    trailText = state.result.categoryName,
                    modifier = Modifier.height(70.dp)
                )
                EditBalance(
                    currentSum = currentSum,
                    currency = currency,
                    onBalanceValueChange = { newSum -> currentSum = newSum }
                )
                CustomListItem(
                    title = {
                        Text(
                            text = stringResource(R.string.account),
                            style = YaFinanceTheme.typography.title
                        )
                    },
                    trailText = state.result.accountName,
                    modifier = Modifier.height(70.dp)
                )
                EditComment(
                    comment = comment ?: "",
                    onEditComment = { newComment -> comment = newComment }
                )
            }
        }
    } else {

    }
}