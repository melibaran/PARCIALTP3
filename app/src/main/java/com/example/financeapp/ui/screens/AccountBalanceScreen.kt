package com.example.financeapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.data.sampleTransactions
import com.example.financeapp.ui.components.BalanceSummaryCard
import com.example.financeapp.ui.components.SectionHeader
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.components.TransactionItem
import com.example.financeapp.ui.components.FinanceBottomBar

@Composable
fun AccountBalanceScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.account_balance_title),
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = {
            FinanceBottomBar(onNavigate = { /* TODO */ })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            item {
                BalanceSummaryCard(
                    totalBalance = 7783.00,
                    totalExpense = 1187.40,
                    progress = 0.3f,
                    goalAmount = 20000.00,
                    description = stringResource(R.string.goal_description)
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
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
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SectionHeader(title = stringResource(R.string.transactions))
                        TextButton(onClick = { /* TODO */ }) {
                            Text(stringResource(R.string.see_all))
                        }
                    }
                }
            }

            items(sampleTransactions) { transaction ->
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(horizontal = 16.dp)) {
                    TransactionItem(
                        iconResId = transaction.iconResId,
                        title = stringResource(transaction.titleResId),
                        category = stringResource(transaction.categoryResId),
                        date = stringResource(transaction.dateResId),
                        amount = String.format(java.util.Locale.US, if (transaction.isExpense) "-$%,.2f" else "$%,.2f", kotlin.math.abs(transaction.amount)),
                        isExpense = transaction.isExpense
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp).background(MaterialTheme.colorScheme.surface))
            }
        }
    }
}

@Composable
private fun IncomeExpenseCard(
    title: String,
    amount: String,
    @DrawableRes iconResId: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = iconResId), contentDescription = title)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(title, style = MaterialTheme.typography.bodyMedium)
                Text(amount, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}
