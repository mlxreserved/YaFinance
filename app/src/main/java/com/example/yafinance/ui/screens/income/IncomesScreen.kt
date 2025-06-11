package com.example.yafinance.ui.screens.income

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.yafinance.R
import com.example.yafinance.domain.models.income.Income
import com.example.yafinance.ui.screens.income.composable.IncomeSuccess
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun IncomesScreen(
    incomes: List<Income>,
    modifier: Modifier = Modifier
) {
    TopAppBarStateProvider.update(TopAppBarState(titleId = R.string.incomes_today, trailId = R.drawable.ic_history))

    IncomeSuccess(incomes = incomes, modifier = Modifier.fillMaxSize())
}