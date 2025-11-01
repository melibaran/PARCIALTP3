package com.example.financeapp.ui.screen.categories.arquitectura

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.theme.*

data class FinanceDisplayData(
    val balance: Double,
    val totalExpense: Double,
    val budget: Double,
    val expensePercentage: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseFinanceScreen(
    title: String,
    navController: NavController,
    data: FinanceDisplayData,
    modifier: Modifier = Modifier,
    topContent: @Composable (() -> Unit)? = null,
    progressBarContent: @Composable (FinanceDisplayData) -> Unit = { defaultProgressBar ->
        DefaultProgressBar(data = defaultProgressBar)
    },
    bottomContent: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            FinanceHeader(
                title = title,
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("notifications") }
            )
        },
        containerColor = Caribbean_green
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Top content (opcional - para el Card de Categories)
            topContent?.invoke()

            Spacer(modifier = Modifier.height(if (topContent != null) 14.dp else 16.dp))

            // Balance and Expense Row (siempre centrado)
            BalanceExpenseRow(data = data, centered = true)

            Spacer(modifier = Modifier.height(16.dp))

            // Progress bar section
            progressBarContent(data)

            Spacer(modifier = Modifier.height(20.dp))

            // Bottom content (Grid + posible botón)
            bottomContent()
        }
    }
}

@Composable
private fun BalanceExpenseRow(
    data: FinanceDisplayData,
    centered: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (centered) Arrangement.Center else Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_up),
                    contentDescription = "Balance Icon",
                    modifier = Modifier.size(16.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.total_balance),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.White,
                        fontSize = 12.sp,
                    ),
                )
            }
            Text(
                text = "$${data.balance}",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color.White,
                    fontSize = 26.sp,
                )
            )
        }

        VerticalDivider(
            modifier = Modifier
                .height(48.dp)
                .padding(horizontal = 14.dp),
            thickness = 1.dp,
            color = Light_green
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (centered) Arrangement.Center else Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_down),
                    contentDescription = "Expense Icon",
                    modifier = Modifier.size(16.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.total_expense),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.White,
                        fontSize = 12.sp,
                    ),
                )
            }
            Text(
                text = "-$${data.totalExpense}",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    fontSize = 26.sp,
                    color = Ocean_blue
                )
            )
        }
    }
}

@Composable
fun DefaultProgressBar(
    data: FinanceDisplayData,
    progressColor: Color = Fence_green,
    showCheckIcon: Boolean = true,
    description: String = ""
) {
    Column(
        modifier = Modifier.padding(horizontal = 21.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Honeydew)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth(data.expensePercentage / 100f)
                        .background(progressColor)
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${data.expensePercentage}%",
                        modifier = Modifier.padding(start = 8.dp),
                        color = Honeydew,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 12.sp,
                        )
                    )
                    Text(
                        text = "${data.budget}",
                        modifier = Modifier.padding(end = 8.dp),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = progressColor,
                            fontSize = 12.sp,
                        )
                    )
                }
            }
        }

        if (showCheckIcon) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.check),
                    contentDescription = "Check Icon",
                    tint = Void,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = description,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Fence_green,
                        fontSize = 14.sp,
                    )
                )
            }
        }
    }
}

@Composable
fun FinanceGridContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
//            .weight(1f)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
            .background(Honeydew)
    ) {
        content()
    }
}