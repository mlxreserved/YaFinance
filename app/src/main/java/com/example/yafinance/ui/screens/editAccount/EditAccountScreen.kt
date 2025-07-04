package com.example.yafinance.ui.screens.editAccount

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.LocalSnackbarViewModel
import com.example.yafinance.ui.LocalTopAppBarViewModel
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.editAccount.composable.CustomBottomSheet
import com.example.yafinance.ui.screens.editAccount.composable.EditAccountContent
import com.example.yafinance.ui.utils.formatWithoutSpaces
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.toUserMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit,
    onSuccess: () -> Unit,
    sum: String,
    currency: String,
    id: Int,
    name: String,
    modifier: Modifier = Modifier,
    editViewModel: EditAccountViewModel = viewModel(factory = viewModelFactory)
) {
    var currentSum by rememberSaveable { mutableStateOf(sum) }
    var currentCurrency by rememberSaveable { mutableStateOf(currency) }
    var accountName by rememberSaveable { mutableStateOf(name) }
    var showBottomSheet by remember { mutableStateOf(false) }

    val topAppBarViewModel = LocalTopAppBarViewModel.current
    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        topAppBarViewModel.update(
            TopAppBarState(
                titleId = R.string.my_account,
                trailId = R.drawable.ic_save,
                leadId = R.drawable.ic_cross,
                onTrailIconClick = {
                    editViewModel.onApplyEditAccountInfo(
                        id = id,
                        name = accountName,
                        sum = currentSum,
                        currency = currentCurrency
                    )
                },
                onLeadIconClick = onLeadIconClick
            )
        )
    }

    val editAccountState by editViewModel.editAccountState.collectAsStateWithLifecycle()


    when (val state = editAccountState) {
        ScreenState.Empty -> {
            EditAccountContent(
                accountName = accountName,
                currency = currentCurrency,
                currentSum = currentSum,
                onBalanceValueChange = { newSum -> currentSum = newSum },
                onAccountNameChange = { newAccountName -> accountName = newAccountName },
                onChangeCurrencyClick = { showBottomSheet = true },
                onTrailIconClick = {
                    editViewModel.onApplyEditAccountInfo(
                        id = id,
                        name = accountName,
                        sum = currentSum,
                        currency = currentCurrency
                    )
                },
                onLeadIconClick = onLeadIconClick
            )
        }

        is ScreenState.Error -> {
            EditAccountContent(
                accountName = accountName,
                currency = currentCurrency,
                currentSum = currentSum,
                onBalanceValueChange = { input -> currentSum = input },
                onAccountNameChange = { newAccountName -> accountName = newAccountName },
                onChangeCurrencyClick = { showBottomSheet = true },
                onTrailIconClick = {
                    editViewModel.onApplyEditAccountInfo(
                        id = id,
                        name = accountName,
                        sum = currentSum,
                        currency = currentCurrency
                    )
                },
                onLeadIconClick = onLeadIconClick
            )
            snackbarViewModel.showMessage(state.message.toUserMessage(context))
        }

        ScreenState.Loading -> {
            LoadingScreen(
                screenTitleId = R.string.my_account,
                modifier = Modifier.fillMaxSize()
            )
        }

        is ScreenState.Success<Account> -> {
            snackbarViewModel.showMessage(stringResource(R.string.success_edit_account))
            editViewModel.setGlobalCurrency(currentCurrency)
            editViewModel.setGlobalAccountName(accountName)
            editViewModel.setGlobalBalance(currentSum.formatWithoutSpaces())
            onSuccess()
        }
    }
    if (showBottomSheet) {
        CustomBottomSheet(
            onDismiss = { showBottomSheet = false },
            onSelectCurrency = { selectedCurrency -> currentCurrency = selectedCurrency}
        )
    }
}