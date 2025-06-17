package com.example.yafinance.ui.screens.editAccount

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.screens.editAccount.composable.EditAccountItem
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun EditAccountScreen(
    navController: NavHostController,
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
                editViewModel.changeAccountInfo(id = id, name = name, sum = currentSum, currency = currency)
            },
            onLeadIconClick = {
                navController.navigateUp()
            }
        )
    )

    val context = LocalContext.current

    val editAccountState by editViewModel.editAccountState.collectAsStateWithLifecycle()


    when(val state = editAccountState){
        ScreenState.Empty -> {
            EditAccountItem(currency =  currency, currentSum = currentSum, onTextChange = { input -> currentSum = input })
        }
        is ScreenState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()

        }
        ScreenState.Loading -> {
            LoadingScreen(screenTitleId = R.string.my_account)
        }
        is ScreenState.Success<Account> -> {
            Toast.makeText(context, "Все супер", Toast.LENGTH_SHORT).show()
            navController.navigateUp()
        }
    }


}