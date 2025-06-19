package com.example.yafinance.ui.screens.editAccount

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.SnackbarViewModel
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.editAccount.composable.EditAccountItem
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun EditAccountScreen(
    snackbarViewModel: SnackbarViewModel,
    onLeadIconClick: () -> Unit,
    onSuccess: () -> Unit,
    sum: String,
    currency: String,
    id: Int,
    name: String,
    editViewModel: EditAccountViewModel = hiltViewModel()
) {
    var currentSum by rememberSaveable { mutableStateOf(sum) }

    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.my_account,
            trailId = R.drawable.ic_save,
            leadId = R.drawable.ic_cross,
            onTrailIconClick = {
                editViewModel.onApplyEditAccountInfo(id = id, name = name, sum = currentSum, currency = currency)
            },
            onLeadIconClick = onLeadIconClick
        )
    )

    val editAccountState by editViewModel.screenState.collectAsStateWithLifecycle()


    when(val state = editAccountState){
        ScreenState.Empty -> {
            EditAccountItem(currency =  currency, currentSum = currentSum, onTextChange = { input -> currentSum = input })
        }
        is ScreenState.Error -> {
            snackbarViewModel.showMessage(state.message)
        }
        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.my_account)
        }
        is ScreenState.Success<Account> -> {
            snackbarViewModel.showMessage(stringResource(R.string.ok))
            onSuccess()
        }
    }


}