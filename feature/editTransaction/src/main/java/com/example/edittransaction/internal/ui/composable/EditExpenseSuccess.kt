package com.example.edittransaction.internal.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.category.Category
import com.example.domain.model.expense.ExpenseDetailed
import com.example.domain.model.expense.ExpenseUpdate
import com.example.edittransaction.R
import com.example.edittransaction.internal.ui.ResponseOfEdit
import com.example.edittransaction.internal.ui.composable.components.CustomBottomSheet
import com.example.edittransaction.internal.ui.composable.components.DeleteButton
import com.example.edittransaction.internal.ui.composable.components.EditBalance
import com.example.edittransaction.internal.ui.composable.components.EditCategory
import com.example.edittransaction.internal.ui.composable.components.EditComment
import com.example.edittransaction.internal.ui.composable.components.EditDate
import com.example.edittransaction.internal.ui.composable.components.EditTime
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.components.datePicker.CustomDatePicker
import com.example.ui.components.timePicker.CustomTimePicker
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner
import com.example.ui.data.state.ScreenState
import com.example.ui.extensions.toUserMessage
import com.example.utils.extensions.string.formatWithoutSpaces
import com.example.utils.extensions.string.toStringWithZone
import java.util.Date

@Composable
fun EditExpenseSuccess(
    onSaveUpdate: (ExpenseUpdate) -> Unit,
    expense: ExpenseDetailed,
    onStartDateSelected: (Long?) -> Unit,
    onStartTimeSelected: (Int?, Int?) -> Unit,
    onCategoryClick: () -> Unit,
    categoriesState: ScreenState<List<Category>>,
    onTrailIconClick: () -> Unit,
    onLeadIconClick: () -> Unit,
    onClick: () -> Unit,
    isConnected: Boolean,
    deleteState: ResponseOfEdit,
    updateState: ResponseOfEdit,
    startDate: Date,
    startTime: Date,
    modifier: Modifier = Modifier
) {
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    var currentCategoryId by rememberSaveable { mutableStateOf(expense.categoryId) }
    var currentCategory by rememberSaveable { mutableStateOf(expense.categoryName) }
    var comment by rememberSaveable { mutableStateOf(expense.comment) }
    var currentSum by rememberSaveable { mutableStateOf(expense.sum) }
    var currency by rememberSaveable { mutableStateOf(expense.currency) }

    when (val state = updateState) {
        is ResponseOfEdit.Error -> {
            snackbarViewModel.showMessage(
                message = state.message.toUserMessage(
                    context
                )
            )
            onTrailIconClick()
        }

        ResponseOfEdit.Loading -> {}
        ResponseOfEdit.Success -> {
            onTrailIconClick()
        }
    }

    when (val state = deleteState) {
        is ResponseOfEdit.Error -> {
            snackbarViewModel.showMessage(
                message = state.message.toUserMessage(
                    context
                )
            )
            onTrailIconClick()
        }

        ResponseOfEdit.Loading -> {}
        ResponseOfEdit.Success -> {
            onTrailIconClick()
        }
    }

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    leadIconId = R.drawable.ic_back,
                    trailIconId = R.drawable.ic_save,
                    titleId = R.string.my_expenses,
                    onLeadIconClick = onLeadIconClick,
                    onTrailIconClick = {
                        onSaveUpdate(
                            ExpenseUpdate(
                                accountId = expense.accountId,
                                categoryId = currentCategoryId,
                                amount = currentSum.formatWithoutSpaces(),
                                transactionDate = startDate.toStringWithZone(),
                                comment = comment,
                                localId = expense.localId
                            )
                        )
                    }
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
        Column(modifier = modifier.padding(top = innerPadding.calculateTopPadding())) {
            EditCategory(
                currentCategory = currentCategory,
                onCategoryClick = {
                    showBottomSheet = true
                    onCategoryClick()
                }
            )
            EditBalance(
                currentSum = currentSum,
                currency = currency,
                onBalanceValueChange = { newSum -> currentSum = newSum }
            )
            EditDate(
                startDate = startDate,
                onDateClick = { showDatePicker = true }
            )
            EditTime(
                startTime = startTime,
                onTimeClick = { showTimePicker = true }
            )
            EditComment(
                comment = comment ?: "",
                onEditComment = { newComment -> comment = newComment }
            )
            DeleteButton(
                onClick = onClick,
                text = R.string.delete_income,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }

        if (showTimePicker) {
            CustomTimePicker(
                selectedTime = startTime.time,
                onTimeSelected = onStartTimeSelected,
                onDismiss = { showTimePicker = false }
            )
        }

        if (showDatePicker) {
            CustomDatePicker(
                selectedDate = startDate.time,
                onDateSelected = onStartDateSelected,
                onDismiss = { showDatePicker = false }
            )
        }
        if (showBottomSheet) {
            CustomBottomSheet(
                onDismiss = { showBottomSheet = false },
                onSelectCategory = { selectedCategory ->
                    currentCategoryId = selectedCategory.id
                    currentCategory = selectedCategory.title
                },
                categoriesState = categoriesState
            )
        }
    }
}