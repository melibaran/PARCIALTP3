package com.example.financeapp.ui.screen.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.components.TransactionListItem
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryDatePicker
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
    var showDatePicker by remember { mutableStateOf(false) }

    val coloresDeCirculo = remember {
        listOf(
            Light_blue,
            Vivid_blue,
            Ocean_blue,
            Vivid_blue,
            Light_blue,
        )
    }

    val (showIncome, setShowIncome) = remember { mutableStateOf(false) }
    val (showExpense, setShowExpense) = remember { mutableStateOf(false) }

    val filteredTransactions = when {
        showIncome -> uiState.transactions.filter { it.isIncome }
        showExpense -> uiState.transactions.filter { !it.isIncome }
        else -> uiState.transactions
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
    ) {
        TopBar(
            title = "Transaction",
            showBackButton = true,
            centerTitle = true,
            onNotificationClick = { navController.navigate("notifications") },
            containerColor = Caribbean_green
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
                        text = "$${uiState.balance}",
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
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            setShowIncome(true)
                            setShowExpense(false)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = if (showIncome) Ocean_blue else Honeydew
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.income),
                            contentDescription = "Income",
                            tint = if (showIncome) Honeydew else Caribbean_green,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Income",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = if (showIncome) Honeydew else Void
                            )
                        )
                        Text(
                            text = "$${uiState.expenseGoal}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                color = if (showIncome) Color.White else Void
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Expense Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            setShowExpense(true)
                            setShowIncome(false)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = if (showExpense) Ocean_blue else Honeydew
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.expense),
                            contentDescription = "Expense",
                            tint = if (showExpense) Honeydew else Ocean_blue,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Expense",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = if (showExpense) Honeydew else Void
                            )
                        )
                        Text(
                            text = "$${uiState.totalExpense}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                color = if (showExpense) Color.White else Ocean_blue
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
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
                    .padding(16.dp)
            ) {
                val availableMonths = viewModel.getAvailableMonths()
                var globalIndex = 0
                var displayedMonthCount = 0

                availableMonths.forEach { month ->
                    val monthTransactions = when {
                        showIncome -> viewModel.getTransactionsByMonth(month).filter { it.isIncome }
                        showExpense -> viewModel.getTransactionsByMonth(month).filter { !it.isIncome }
                        else -> viewModel.getTransactionsByMonth(month)
                    }

                    if (monthTransactions.isNotEmpty()) {
                        val isFirstDisplayedMonth = displayedMonthCount == 0

                        item(key = "header_$month") {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        bottom = 8.dp,
                                        top = if (isFirstDisplayedMonth) 16.dp else 8.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    month,
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                        color = Fence_green,
                                        fontSize = 20.sp,
                                    ),
                                    modifier = Modifier.weight(1f)
                                )

                                if (isFirstDisplayedMonth) {
                                    val calendarInteractionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                                    val isCalendarPressed by calendarInteractionSource.collectIsPressedAsState()

                                    val calendarIcon = if (isCalendarPressed) {
                                        R.drawable.calendar_pressed
                                    } else {
                                        R.drawable.calendar_default
                                    }

                                    IconButton(
                                        onClick = { showDatePicker = true },
                                        modifier = Modifier
                                            .size(32.26.dp, 30.dp)
                                            .clip(RoundedCornerShape(4.dp)),
                                        interactionSource = calendarInteractionSource
                                    ) {
                                        Icon(
                                            painter = painterResource(calendarIcon),
                                            contentDescription = "Calendar",
                                            tint = Color.Unspecified,
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                }
                            }
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
                        displayedMonthCount++
                    }
                }
            }
        }
    }

    CategoryDatePicker(
        showDatePicker = showDatePicker,
        onDismiss = { showDatePicker = false }
    )
}