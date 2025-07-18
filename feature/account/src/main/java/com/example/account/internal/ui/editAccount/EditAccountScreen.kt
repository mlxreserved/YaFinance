package com.example.account.internal.ui.editAccount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.account.R
import com.example.account.internal.ui.editAccount.composable.CustomBottomSheet
import com.example.account.internal.ui.editAccount.composable.EditAccountContent
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.domain.model.account.Account
import com.example.ui.LocalSnackbarViewModel
import com.example.ui.components.screens.LoadingScreen
import com.example.ui.components.topAppBar.CustomTopAppBar
import com.example.ui.components.topAppBar.NetworkStatusBanner
import com.example.ui.data.state.ScreenState
import com.example.ui.extensions.toUserMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditAccountScreen(
    isConnected: Boolean,
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

    val snackbarViewModel = LocalSnackbarViewModel.current
    val context = LocalContext.current

    val editAccountState by editViewModel.editAccountState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    leadIconId = R.drawable.ic_back,
                    trailIconId = R.drawable.ic_save,
                    titleId = R.string.my_account,
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
                NetworkStatusBanner(
                    isConnected = isConnected,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = YaFinanceTheme.colors.primaryBackground)
                )
            }
        }
    ) { innerPadding ->
        when (val state = editAccountState) {
            ScreenState.Empty -> {
                EditAccountContent(
                    accountName = accountName,
                    currency = currentCurrency,
                    currentSum = currentSum,
                    onBalanceValueChange = { newSum -> currentSum = newSum },
                    onAccountNameChange = { newAccountName -> accountName = newAccountName },
                    onChangeCurrencyClick = { showBottomSheet = true },
                    modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding()),
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
                    modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding()),
                )
                snackbarViewModel.showMessage(state.message.toUserMessage(context))
            }

            ScreenState.Loading -> {
                LoadingScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is ScreenState.Success<Account> -> {
                snackbarViewModel.showMessage(stringResource(R.string.success_edit_account))
                onSuccess()
            }
        }
        if (showBottomSheet) {
            CustomBottomSheet(
                onDismiss = { showBottomSheet = false },
                onSelectCurrency = { selectedCurrency -> currentCurrency = selectedCurrency }
            )
        }
    }
}