package com.example.account.api.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.example.account.internal.ui.account.AccountsScreen
import com.example.account.internal.ui.editAccount.EditAccountScreen
import com.example.domain.model.account.Account
import com.example.utils.extensions.string.formatWithoutSpaces
import kotlinx.serialization.Serializable

@Serializable
object AccountAllRoutes

@Serializable
object AccountRoute

@Serializable
data class AccountEditRoute(
    val id: Int,
    val name: String,
    val sum: String,
    val currency: String
)

fun NavController.navigateToEditAccount(
    account: Account,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(
        route = AccountEditRoute(
            sum = account.sum.formatWithoutSpaces(),
            currency = account.currency,
            id = account.id,
            name = account.name
        )
    ) {
        navOptions()
    }
}

fun NavGraphBuilder.editAccountScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit,
    onSuccess: () -> Unit

) {
    composable<AccountEditRoute> {
        val args = it.toRoute<AccountEditRoute>()

        EditAccountScreen(
            isConnected = isConnected,
            viewModelFactory = viewModelFactory,
            onLeadIconClick = onLeadIconClick,
            onSuccess = onSuccess,
            sum = args.sum,
            currency = args.currency,
            id = args.id,
            name = args.name
        )
    }
}

fun NavGraphBuilder.accountBase(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onEditIconClick: (Account) -> Unit,
    editDestination: NavGraphBuilder.() -> Unit
) {
    navigation<AccountAllRoutes>(startDestination = AccountRoute) {
        composable<AccountRoute> {
            AccountsScreen(
                isConnected = isConnected,
                onEditClick = onEditIconClick,
                viewModelFactory = viewModelFactory
            )
        }
        editDestination()
    }
}