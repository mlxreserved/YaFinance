package com.example.ui.components.topAppBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import com.example.design.theme.customTheme.YaFinanceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    @DrawableRes leadIconId: Int? = null,
    @DrawableRes trailIconId: Int? = null,
    @StringRes titleId: Int? = null,
    onLeadIconClick: (() -> Unit)? = null,
    onTrailIconClick: (() -> Unit)? = null,

) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            leadIconId?.let { leadIcon ->
                IconButton(
                    onClick = { onLeadIconClick?.invoke() }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(leadIcon),
                        contentDescription = null
                    )
                }
            }
        },
        title = {
            titleId?.let { title ->
                Text(
                    text = stringResource(title),
                    style = YaFinanceTheme.typography.header
                )
            }
        },
        actions = {
            trailIconId?.let { trailIcon ->
                IconButton(
                    onClick = { onTrailIconClick?.invoke() }
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