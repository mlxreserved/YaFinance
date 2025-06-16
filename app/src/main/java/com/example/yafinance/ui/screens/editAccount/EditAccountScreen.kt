package com.example.yafinance.ui.screens.editAccount

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.yafinance.R
import com.example.yafinance.domain.models.account.Account
import com.example.yafinance.ui.composable.screens.LoadingScreen
import com.example.yafinance.ui.theme.customTheme.YaFinanceTheme
import com.example.yafinance.ui.utils.state.ScreenState
import com.example.yafinance.ui.utils.state.TopAppBarState
import com.example.yafinance.ui.utils.state.TopAppBarStateProvider

@Composable
fun EditAccountScreen(
    navController: NavHostController,
    sum: String,
    currency: String,
    id: Int,
    name: String,
    editViewModel: EditAccountViewModel = hiltViewModel()
) {
    var currentSum by rememberSaveable { mutableStateOf(sum) }

    TopAppBarStateProvider.update(
        TopAppBarState(
            titleId = R.string.my_account,
            trailId = R.drawable.save,
            leadId = R.drawable.cancel,
            onTrailIconClick = {
                editViewModel.changeAccountInfo(id = id, name = name, sum = currentSum, currency = currency)
            },
            onLeadIconClick = {
                navController.navigateUp()
            }
        )
    )

    val context = LocalContext.current

    val editAccountState by editViewModel.editAccountState.collectAsStateWithLifecycle()


    when(val state = editAccountState){
        ScreenState.Empty -> {
            Column {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(YaFinanceTheme.colors.white)
                        .height(56.dp)
                ) {

                    Text(
                        text = stringResource(R.string.balance_lead_icon),
                        style = YaFinanceTheme.typography.emoji,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )

                    Spacer(Modifier.width(16.dp))

                    Text(
                        text = "Баланс",
                        style = YaFinanceTheme.typography.title,
                        modifier = Modifier.weight(0.35f)
                    )

                    TextField(
                        value = currentSum,
                        onValueChange = { input -> currentSum = input },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = YaFinanceTheme.typography.title.copy(textAlign = TextAlign.End),
                        visualTransformation = VisualTransformation.None,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = YaFinanceTheme.colors.white,
                            unfocusedContainerColor = YaFinanceTheme.colors.white,
                            disabledContainerColor = YaFinanceTheme.colors.white,
                            focusedIndicatorColor = YaFinanceTheme.colors.white,
                            unfocusedIndicatorColor = YaFinanceTheme.colors.white,
                            disabledIndicatorColor = YaFinanceTheme.colors.white,
                        ),
                        trailingIcon = {
                            Text(text = currency, style = YaFinanceTheme.typography.title)
                        },
                        modifier = Modifier
                            .weight(0.45f)
                    )

                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .clip(shape = RectangleShape)
                            .fillMaxHeight()
                            .width(48.dp)
                            .background(YaFinanceTheme.colors.deleteColor)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.delete),
                            contentDescription = "Очистить",
                            tint = YaFinanceTheme.colors.white,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                HorizontalDivider(modifier = Modifier.fillMaxWidth())
            }
        }
        is ScreenState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()

        }
        ScreenState.Loading -> {
            LoadingScreen()
        }
        is ScreenState.Success<Account> -> {
            Toast.makeText(context, "Все супер", Toast.LENGTH_SHORT).show()
            navController.navigateUp()
        }
    }


}