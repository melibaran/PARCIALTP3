package com.example.financeapp.ui.screen.transaction

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.components.TransactionListItem
import com.example.financeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailScreen(
    onBackClick: () -> Unit,
    transactions: List<TransactionItem> = emptyList(),
    totalBalance: Double = 7783.00,
    totalIncome: Double = 4120.00,
    totalExpense: Double = 1187.40
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    "Transaction",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                        color = Void
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(R.drawable.bring_back),
                        contentDescription = "Back",
                        tint = Void
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        painter = painterResource(R.drawable.bell),
                        contentDescription = "Notifications",
                        tint = Void
                    )
                }
            }
        )

        // Balance Section
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Honeydew)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Total Balance",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 15.sp
                        )
                    )
                    Text(
                        text = "$$totalBalance",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 24.sp
                        )
                    )
                }
            }

            // Income and Expense Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Income Card
                Card(
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Honeydew)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.income),
                            contentDescription = "Income",
                            tint = Caribbean_green,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Income",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                            )
                        )
                        Text(
                            text = "$$totalIncome",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                color = Void
                            )
                        )
                    }
                }

                // Expense Card
                Card(
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Honeydew)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.expense),
                            contentDescription = "Expense",
                            tint = Ocean_blue,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Expense",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                            )
                        )
                        Text(
                            text = "$$totalExpense",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                color = Ocean_blue
                            )
                        )
                    }
                }
            }
        }

        // Transactions List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                .background(Honeydew)
                .padding(top = 16.dp)
        ) {
            item {
                Text(
                    text = "April",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            items(transactions) { transaction ->
                TransactionListItem(
                    transaction = transaction,
                    circleBgColor = Light_blue,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}
