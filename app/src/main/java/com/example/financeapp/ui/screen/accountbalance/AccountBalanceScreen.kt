package com.example.financeapp.ui.screen.accountbalance

import androidx.annotation.DrawableRes
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
import com.example.financeapp.data.sampleTransactions
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.components.TransactionListItem
import com.example.financeapp.ui.screen.transaction.TransactionItem as TxItem
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Fence_green

@Composable
fun AccountBalanceScreen(navController: NavController) {
    val coloresDeCirculo = remember {
        listOf(Light_blue, Vivid_blue, Ocean_blue, Vivid_blue, Light_blue)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.account_balance_title),
                showBackButton = true,
                onBackClick = { navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                } },
                onNotificationClick = { navController.navigate("notifications") },
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
            // Balance Header
            BalanceHeader(
                totalBalance = 7783.00,
                totalExpense = 1187.40,
                budget = 20000.00,
                progressPercentage = 30
            )


            Spacer(modifier = Modifier.height(8.dp))

            // Cards de Income y Expense (en el fondo verde)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IncomeExpenseCard(
                    title = stringResource(R.string.income),
                    amount = "$4,000.00",
                    iconResId = R.drawable.arrow_up,
                    modifier = Modifier.weight(1f)
                )
                IncomeExpenseCard(
                    title = stringResource(R.string.expense),
                    amount = "$1,187.40",
                    iconResId = R.drawable.arrow_down,
                    modifier = Modifier.weight(1f),
                    isExpense = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Transactions List (bloque Honeydew arranca aquí)
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
                    .padding(16.dp)
            ) {
                // Transactions header
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.transactions),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Fence_green
                        )
                        TextButton(onClick = { /* TODO */ }) {
                            Text(
                                text = stringResource(R.string.see_all),
                                color = Fence_green
                            )
                        }
                    }
                }

                // Lista de transacciones
                itemsIndexed(sampleTransactions) { index, transaction ->
                    val iconId = if (transaction.iconResId == R.drawable.salary) R.drawable.salary_white else transaction.iconResId
                    val mapped = TxItem(
                        id = "tx_$index",
                        iconId = iconId,
                        title = stringResource(transaction.titleResId),
                        dateTime = stringResource(transaction.dateResId),
                        category = stringResource(transaction.categoryResId),
                        amount = transaction.amount,
                        isIncome = !transaction.isExpense,
                        month = ""
                    )

                    val circleColor = coloresDeCirculo.getOrElse(index % coloresDeCirculo.size) { Light_blue }

                    TransactionListItem(
                        transaction = mapped,
                        circleBgColor = circleColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
private fun IncomeExpenseCard(
    title: String,
    amount: String,
    @DrawableRes iconResId: Int,
    modifier: Modifier = Modifier,
    isExpense: Boolean = false
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = amount,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (isExpense) Ocean_blue else MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

