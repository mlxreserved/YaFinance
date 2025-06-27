package com.example.yafinance.ui.composable.topAppBar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.state.TopAppBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(topAppBarState: TopAppBarState) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            topAppBarState.leadId?.let { leadIcon ->
                IconButton(
                    onClick = { topAppBarState.onLeadIconClick?.invoke() }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(leadIcon),
                        contentDescription = null
                    )
                }
            }
        },
        title = {
            topAppBarState.titleId?.let { title ->
                Text(
                    text = stringResource(title),
                    style = YaFinanceTheme.typography.header
                )
            }
        },
        actions = {
            topAppBarState.trailId?.let { trailIcon ->
                IconButton(
                    onClick = { topAppBarState.onTrailIconClick?.invoke() }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(trailIcon),
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = YaFinanceTheme.colors.primaryBackground)
    )
}