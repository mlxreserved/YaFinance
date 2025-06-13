package com.example.yafinance.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.yafinance.R

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie))
    val logoAnimationState =
        animateLottieCompositionAsState(composition = composition)
    LottieAnimation(
        composition = composition,
        progress = { logoAnimationState.progress }
    )
    if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
        onSplashFinished()
    }
}