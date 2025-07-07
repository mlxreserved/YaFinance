package com.example.ui.components.topAppBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.design.theme.customTheme.YaFinanceTheme
import com.example.ui.R

@Composable
fun NetworkStatusBanner(
    isConnected: Boolean,
    modifier: Modifier = Modifier
) {
    val visible by rememberUpdatedState(!isConnected)

    AnimatedVisibility(
        visible = visible,
        modifier = modifier

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(28.dp)
                    .clip(CircleShape)
                    .background(color = YaFinanceTheme.colors.errorColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.error_internet),
                    color = YaFinanceTheme.colors.white,
                    style = YaFinanceTheme.typography.title,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}