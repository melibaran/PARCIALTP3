package com.example.financeapp.ui.screen.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TransactionListItem
import com.example.financeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    transactions: List<TransactionItem> = emptyList(),
    totalBalance: Double = 7783.00,
    totalIncome: Double = 4120.00,
    totalExpense: Double = 1187.40,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val coloresDeCirculo = remember {
        listOf(
            Light_blue,
            Vivid_blue,
            Ocean_blue,
            Vivid_blue,
            Light_blue,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
    ) {
        TopAppBar(
            title = {
                Text(
                    "Transaction",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                        color = Fence_green,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        painter = painterResource(R.drawable.bring_back),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* TODO */ }) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bell),
                            contentDescription = "Notifications",
                            modifier = Modifier.size(24.dp),
                            tint = Void
                        )
                    }
                }
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Caribbean_green,

            ),

        )

        // Total Balance:
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Honeydew)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Total Balance",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Fence_green,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = "$${uiState.balance}", // <-- Usando el valor del backend
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
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Income Card
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Honeydew)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
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
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                            )
                        )
                        Text(
                            text = "$${uiState.expenseGoal}", // <-- Usando el valor del backend
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                color = Void
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Expense Card
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Honeydew)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
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
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                            )
                        )
                        Text(
                            text = "$${uiState.totalExpense}", // <-- Usando el valor del backend
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                color = Ocean_blue
                            )
                        )
                    }
                }
            }
        }


        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Fence_green)
            }
        } else if (uiState.error != null) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Error: ${uiState.error}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color.Red,
                            fontSize = 14.sp,
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.loadTransactions() }) {
                        Text("Reintentar")
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
                    .padding(16.dp)
            ) {
                val availableMonths = viewModel.getAvailableMonths()

                var globalIndex = 0
                availableMonths.forEach { month ->
                    val monthTransactions = viewModel.getTransactionsByMonth(month)

                    if (monthTransactions.isNotEmpty()) {
                        // Header del mes
                        item(key = "header_$month") {
                            Text(
                                month,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                    color = Fence_green,
                                    fontSize = 20.sp,
                                ),
                                modifier = Modifier.padding(bottom = 8.dp, top = if (globalIndex > 0) 8.dp else 0.dp)
                            )
                        }

                        itemsIndexed(
                            monthTransactions,
                            key = { _, item -> item.id }
                        ) { localIndex, transaction ->
                            val itemColor = coloresDeCirculo.getOrElse(globalIndex + localIndex) { Light_blue }

                            TransactionListItem(
                                transaction = transaction,
                                circleBgColor = itemColor
                            )
                        }

                        globalIndex += monthTransactions.size
                    }
                }

                if (availableMonths.isEmpty() || uiState.transactions.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No hay transacciones disponibles",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Fence_green,
                                    fontSize = 16.sp,
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}