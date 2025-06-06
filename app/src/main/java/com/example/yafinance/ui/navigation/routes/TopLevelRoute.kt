package com.example.yafinance.ui.navigation.routes

import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelRoute <T : Any> (val name: String, val route: T, val icon: ImageVector)