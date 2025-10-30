package com.example.financeapp.ui.screens

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.data.foodTransactions
import com.example.financeapp.ui.components.BalanceSummaryCard
import com.example.financeapp.ui.components.SectionHeader
import com.example.financeapp.ui.components.TopBar

@Composable
fun FoodScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.food_title),
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {
            Button(
                onClick = { /* TODO: Add expense callback */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text(stringResource(R.string.add_expenses), modifier = Modifier.padding(vertical = 8.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
             // The existing Bottom Navigation Bar will be used here, with space
            Spacer(modifier = Modifier.height(80.dp))
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

            foodTransactions.forEach { (monthResId, transactions) ->
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        SectionHeader(title = stringResource(monthResId))
                    }
                }
                items(transactions) { transaction ->
                    Box(modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(horizontal = 16.dp)) {
                        FoodTransactionItem(
                            title = stringResource(transaction.titleResId),
                            date = stringResource(transaction.dateResId),
                            amount = String.format(java.util.Locale.US, "-$%,.2f", transaction.amount)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp).background(MaterialTheme.colorScheme.surface))
            }
        }
    }
}

@Composable
private fun FoodTransactionItem(title: String, date: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food),
                    contentDescription = title
                )
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.bodyLarge)
                Text(date, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Text(amount, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.error)
    }
}
