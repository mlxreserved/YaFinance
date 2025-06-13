package com.example.yafinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.yafinance.ui.navigation.host.FinanceNavGraph
import com.example.yafinance.ui.theme.customTheme.MainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainTheme {
                FinanceNavGraph()
            }
        }
    }
}

