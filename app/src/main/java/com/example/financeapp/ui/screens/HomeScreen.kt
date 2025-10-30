package com.example.financeapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.financeapp.ui.components.BalanceSummaryCard
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.components.TransactionItem

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.hi_welcome_back),
                subtitle = stringResource(R.string.good_morning)
            )
        },
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

            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    HomeInfoCards()
                    Spacer(modifier = Modifier.height(16.dp))
                    FilterTabs()
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            items(sampleTransactions) { transaction ->
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(horizontal = 16.dp)) {
                    TransactionItem(
                        iconResId = transaction.iconResId,
                        title = stringResource(transaction.titleResId),
                        category = stringResource(transaction.categoryResId),
                        date = stringResource(transaction.dateResId),
                    amount = String.format(java.util.Locale.US, if (transaction.isExpense) "-$%,.2f" else "$%,.2f", transaction.amount),
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
private fun HomeInfoCards() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.car),
                    contentDescription = stringResource(R.string.savings_on_goals),
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.savings_on_goals), style = MaterialTheme.typography.bodyMedium)
            }
            VerticalDivider(modifier = Modifier.height(40.dp).padding(horizontal = 8.dp))
            Column(modifier = Modifier.weight(1f)) {
                InfoRow(iconResId = R.drawable.salary_white, title = stringResource(R.string.revenue_last_week), amount = "$4,000.00")
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(iconResId = R.drawable.food, title = stringResource(R.string.food_last_week), amount = "-$100.00")
            }
        }
    }
}

@Composable
private fun InfoRow(@DrawableRes iconResId: Int, title: String, amount: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = iconResId), contentDescription = title, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(title, style = MaterialTheme.typography.bodySmall)
            Text(amount, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun FilterTabs() {
    var selectedTabIndex by remember { mutableStateOf(2) }
    val tabs = listOf(stringResource(R.string.daily), stringResource(R.string.weekly), stringResource(R.string.monthly))

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.primary,
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            val selected = selectedTabIndex == index
            Tab(
                selected = selected,
                onClick = { selectedTabIndex = index },
                modifier = if (selected) Modifier
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                else Modifier.padding(horizontal = 4.dp)
            ) {
                Text(
                    text = title,
                    color = if (selected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}
