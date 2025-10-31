package com.example.financeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.data.foodTransactions
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.components.TransactionListItem
import com.example.financeapp.ui.screen.transaction.TransactionItem as TxItem
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Fence_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Vivid_blue

@Composable
fun FoodScreen(navController: NavController) {
    val coloresDeCirculo = remember {
        listOf(Light_blue, Vivid_blue, Ocean_blue, Vivid_blue, Light_blue)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.food_title),
                showBackButton = true,
                onBackClick = { navController.popBackStack() },
                containerColor = Caribbean_green
            )
        },
        containerColor = Caribbean_green
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // Total Balance y Total Expense Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.arrow_up),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.total_balance),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                    Text(
                        text = "$7,783.00",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }

                com.example.financeapp.ui.components.VerticalDivider(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(horizontal = 14.dp),
                    thickness = 1.dp,
                    color = Light_green
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.arrow_down),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.total_expense),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                    Text(
                        text = "-$1,187.40",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Ocean_blue
                        )
                    )
                }
            }

            // Progress Bar
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
                                .fillMaxWidth(0.3f)
                                .background(Fence_green)
                        )
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "30%",
                                modifier = Modifier.padding(start = 8.dp),
                                color = Honeydew,
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                text = "$20,000.00",
                                modifier = Modifier.padding(end = 8.dp),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Fence_green
                                )
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = "Check",
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.goal_description),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Fence_green
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Transactions List agrupadas por mes
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
                    .padding(16.dp)
            ) {
                var monthGlobalIndex = 0
                foodTransactions.forEach { (monthResId, transactions) ->
                    // Month header
                    item {
                        Text(
                            text = stringResource(monthResId),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Fence_green,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    // Transactions for this month
                    itemsIndexed(transactions) { index, transaction ->
                        val mapped = TxItem(
                            id = "food_${monthResId}_$index",
                            title = stringResource(transaction.titleResId),
                            amount = transaction.amount,
                            category = if (transaction.categoryResId != 0) stringResource(transaction.categoryResId) else "",
                            dateTime = stringResource(transaction.dateResId),
                            iconId = transaction.iconResId,
                            isIncome = !transaction.isExpense,
                            month = stringResource(monthResId)
                        )

                        val circleColor = coloresDeCirculo.getOrElse((monthGlobalIndex + index) % coloresDeCirculo.size) { Light_blue }

                        TransactionListItem(
                            transaction = mapped,
                            circleBgColor = circleColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        )
                    }
                    monthGlobalIndex += transactions.size
                }

                // Add Expenses button
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { /* TODO: Add expense callback */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.add_expenses),
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}
