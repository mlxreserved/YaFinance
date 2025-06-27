package com.example.yafinance.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.yafinance.appComponent
import com.example.yafinance.ui.navigation.host.FinanceNavGraph
import com.example.yafinance.ui.theme.customTheme.MainTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {

        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainTheme {
                FinanceNavGraph(viewModelFactory = viewModelFactory)
            }
        }
    }
}