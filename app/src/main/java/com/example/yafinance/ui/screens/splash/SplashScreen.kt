package com.example.yafinance.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.yafinance.R
import com.example.yafinance.ui.navigation.routes.ScreensRoute.ExpensesRoute
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie))
    val logoAnimationState =
        animateLottieCompositionAsState(composition = composition)
    LottieAnimation(
        composition = composition,
        progress = { logoAnimationState.progress }
    )
    if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
        navController.popBackStack()
        navController.navigate(ExpensesRoute)
    }
}