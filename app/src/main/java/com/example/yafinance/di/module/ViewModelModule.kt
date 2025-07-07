//package com.example.yafinance.di.module
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.yafinance.ui.viewModel.NetworkStatusViewModel
//import com.example.yafinance.ui.viewModel.SnackbarViewModel
//import com.example.yafinance.ui.screens.accounts.AccountsViewModel
//import com.example.yafinance.ui.screens.categories.CategoriesViewModel
//import com.example.yafinance.ui.screens.editAccount.EditAccountViewModel
//import com.example.yafinance.ui.screens.expense.ExpensesViewModel
//import com.example.yafinance.ui.screens.history.expensesHistory.ExpensesHistoryViewModel
//import com.example.yafinance.ui.screens.history.incomesHistory.IncomesHistoryViewModel
//import com.example.yafinance.ui.screens.income.IncomesViewModel
//import com.example.yafinance.ui.viewModel.TopAppBarViewModel
//import dagger.Binds
//import dagger.Module
//import dagger.multibindings.IntoMap
//
//@Module
//interface ViewModelModule {
//    @Binds
//    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(NetworkStatusViewModel::class)
//    fun bindNetworkStatusViewModel(networkStatusViewModel: NetworkStatusViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(SnackbarViewModel::class)
//    fun bindSnackbarViewModel(snackbarViewModel: SnackbarViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(TopAppBarViewModel::class)
//    fun bindTopAppBarViewModel(topAppBarViewModel: TopAppBarViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(AccountsViewModel::class)
//    fun bindAccountsViewModel(accountsViewModel: AccountsViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(CategoriesViewModel::class)
//    fun bindCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(EditAccountViewModel::class)
//    fun bindEditAccountViewModel(editAccountViewModel: EditAccountViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ExpensesViewModel::class)
//    fun bindExpensesViewModel(expensesViewModel: ExpensesViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ExpensesHistoryViewModel::class)
//    fun bindExpensesHistoryViewModel(expensesHistoryViewModel: ExpensesHistoryViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(IncomesHistoryViewModel::class)
//    fun bindIncomesHistoryViewModel(incomesHistoryViewModel: IncomesHistoryViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(IncomesViewModel::class)
//    fun bindIncomesViewModel(incomesViewModel: IncomesViewModel): ViewModel
//}