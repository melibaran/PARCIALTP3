package com.example.financeapp.ui.screen.accountbalance

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Fence_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.screen.transaction.TransactionItem as TxItem

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
                    val iconId = getIconForCategory(transaction.titleResId)
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

private fun getIconForCategory(@StringRes titleResId: Int): Int {
    return when (titleResId) {
        R.string.groceries -> R.drawable.groceries_vector
        R.string.rent -> R.drawable.rent_vector
        R.string.transport -> R.drawable.transport_vector
        R.string.food -> R.drawable.food_vector
        R.string.entertainment -> R.drawable.entertaiment_vector
        R.string.gift -> R.drawable.gift_vector
        R.string.salary -> R.drawable.salary_white
        else -> R.drawable.expense
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

