package com.example.yafinance.ui.screens.categories.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.yafinance.R
import com.example.yafinance.ui.composable.CustomListItem
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme

@Composable
fun CategorySearch(trailIcon: ImageVector) {

    TextField(
        value = "",
        onValueChange = {},
        enabled = false,
        label = { Text(stringResource(R.string.find_category)) },
        textStyle = YaFinanceTheme.typography.title,
        trailingIcon = { Icon(trailIcon, contentDescription = null) },
        colors = TextFieldDefaults.colors(
            disabledContainerColor = YaFinanceTheme.colors.tertiaryBackground,
            disabledLabelColor = YaFinanceTheme.colors.tertiaryText,
            disabledTrailingIconColor = YaFinanceTheme.colors.tertiaryText
        ),
        modifier = Modifier.height(56.dp).fillMaxWidth(),
    )

//    CustomListItem(
//        title = {
//            Text(
//                style = YaFinanceTheme.typography.title,
//                text = stringResource(R.string.find_category),
//                color = YaFinanceTheme.colors.tertiaryText
//            )
//        },
//        trailItem =,
//
//        backgroundContainerColor = YaFinanceTheme.colors.tertiaryBackground
//    )
}