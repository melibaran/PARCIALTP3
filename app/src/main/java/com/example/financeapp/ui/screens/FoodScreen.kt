package com.example.financeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.data.foodTransactions
import com.example.financeapp.ui.components.BalanceSummaryCard
import com.example.financeapp.ui.components.SectionHeader
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.components.TransactionItem

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
            // Fixed button style to match design
            Button(
                onClick = { /* TODO: Add expense callback */ },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(stringResource(R.string.add_expenses), color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(vertical = 8.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
             // The existing Bottom Navigation Bar will be used here
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
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        SectionHeader(title = stringResource(monthResId))
                    }
                }
                items(transactions) { transaction ->
                    Box(modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(horizontal = 16.dp)) {
                        TransactionItem(
                            iconResId = transaction.iconResId,
                            title = stringResource(transaction.titleResId),
                            category = if (transaction.categoryResId != 0) stringResource(transaction.categoryResId) else "",
                            date = stringResource(transaction.dateResId),
                            amount = String.format(java.util.Locale.US, if (transaction.isExpense) "-$%,.2f" else "$%,.2f", kotlin.math.abs(transaction.amount)),
                            isExpense = transaction.isExpense
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
