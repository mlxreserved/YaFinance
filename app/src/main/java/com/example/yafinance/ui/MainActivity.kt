package com.example.yafinance.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.account.di.component.AccountComponent
import com.example.category.di.component.CategoryComponent
import com.example.yafinance.appComponent
import com.example.yafinance.ui.navigation.host.FinanceNavGraph
//import com.example.yafinance.ui.theme.customTheme.MainTheme
import com.example.design.theme.customTheme.MainTheme
import com.example.edittransaction.di.component.EditTransactionComponent
import com.example.expense.di.component.ExpenseComponent
import com.example.income.di.component.IncomeComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var globalViewModelFactory: ViewModelProvider.Factory

    private lateinit var expenseComponent: ExpenseComponent
    private lateinit var incomeComponent: IncomeComponent
    private lateinit var accountComponent: AccountComponent
    private lateinit var categoryComponent: CategoryComponent
    private lateinit var editTransactionComponent: EditTransactionComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        appComponent.inject(this)
        if (!::expenseComponent.isInitialized) {
            expenseComponent = appComponent.expenseComponentFactory().create()
        }

        if (!::incomeComponent.isInitialized) {
            incomeComponent = appComponent.incomeComponentFactory().create()
        }

        if (!::accountComponent.isInitialized) {
            accountComponent = appComponent.accountComponentFactory().create()
        }

        if (!::categoryComponent.isInitialized) {
            categoryComponent = appComponent.categoryComponentFactory().create()
        }

        if (!::editTransactionComponent.isInitialized) {
            editTransactionComponent = appComponent.editTransactionComponentFactory().create()
        }

        val expenseViewModelFactory = expenseComponent.viewModelFactory()
        val incomeViewModelFactory = incomeComponent.viewModelFactory()
        val accountViewModelFactory = accountComponent.viewModelFactory()
        val categoryViewModelFactory = categoryComponent.viewModelFactory()
        val editTransactionViewModelFactory = editTransactionComponent.viewModelFactory()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainTheme {
                FinanceNavGraph(
                    globalViewModelFactory = globalViewModelFactory,
                    expenseViewModelFactory = expenseViewModelFactory,
                    accountViewModelFactory = accountViewModelFactory,
                    incomeViewModelFactory = incomeViewModelFactory,
                    categoryViewModelFactory = categoryViewModelFactory,
                    editTransactionViewModelFactory = editTransactionViewModelFactory
                )
            }
        }
    }
}