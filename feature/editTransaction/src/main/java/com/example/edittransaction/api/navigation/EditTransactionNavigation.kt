package com.example.edittransaction.api.navigation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.domain.model.account.Account
import com.example.edittransaction.internal.ui.EditTransactionScreen
import com.example.utils.extensions.string.formatWithoutSpaces
import kotlinx.serialization.Serializable

@Serializable
data class EditTransactionRoute(
    val id: Int?,
    val isAdd: Boolean,
    val isIncome: Boolean
)

fun NavController.navigateToEditTransaction(
    id: Int? = null,
    isAdd: Boolean,
    isIncome: Boolean,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(
        route = EditTransactionRoute(
            id = id,
            isAdd = isAdd,
            isIncome = isIncome,
        )
    ) {
        navOptions()
    }
}

fun NavGraphBuilder.editTransactionScreen(
    isConnected: Boolean,
    viewModelFactory: ViewModelProvider.Factory,
    onLeadIconClick: () -> Unit,
    onSuccess: () -> Unit
) {
    composable<EditTransactionRoute> {
        val args = it.toRoute<EditTransactionRoute>()

        EditTransactionScreen(
            isConnected = isConnected,
            id = args.id,
            isIncome = args.isIncome,
            isAdd = args.isAdd,
            onLeadIconClick = onLeadIconClick,
            onTrailIconClick = onSuccess,
            viewModelFactory = viewModelFactory
        )
    }
}