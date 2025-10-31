package com.example.financeapp.ui.screen.categories.food

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.stringResource
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
import com.example.financeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FoodViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val coloresDeCirculo = remember {
        listOf(
            Light_blue,
            Vivid_blue,
            Ocean_blue,
            Vivid_blue,
            Light_blue
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Food",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Void,
                            fontWeight = FontWeight.SemiBold
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
                    IconButton(onClick = { navController.navigate("notifications") }) {
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
                    containerColor = Caribbean_green
                )
            )
        },
        bottomBar = {
            // Bottom bar con botón Add Expenses centrado (igual que Save en AddExpensesScreen)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Honeydew)
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { navController.navigate("add_expenses") },
                    modifier = Modifier
                        .width(169.dp)
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Caribbean_green
                    ),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text(
                        text = "Add Expenses",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            color = Honeydew,
                            fontSize = 16.sp,
                        )
                    )
                }
            }
        },
        containerColor = Caribbean_green
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Balance and Expense Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Total Balance
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.income),
                            contentDescription = "Balance Icon",
                            modifier = Modifier.size(16.dp),
                            tint = Honeydew
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(id = R.string.total_balance),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Honeydew,
                                fontSize = 12.sp,
                            )
                        )
                    }
                    Text(
                        text = "$${String.format("%.2f", uiState.balance)}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Honeydew,
                            fontSize = 26.sp,
                        )
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(horizontal = 14.dp),
                    thickness = 1.dp,
                    color = Light_green
                )

                // Total Expense
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.expense),
                            contentDescription = "Expense Icon",
                            modifier = Modifier.size(16.dp),
                            tint = Honeydew
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(id = R.string.total_expense),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Honeydew,
                                fontSize = 12.sp,
                            )
                        )
                    }
                    Text(
                        text = "$${String.format("%.2f", uiState.totalExpense)}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            fontSize = 26.sp,
                            color = Ocean_blue
                        )
                    )
                }
            }

            // Progress bar section
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
                                .fillMaxWidth(uiState.expensePercentage / 100f)
                                .background(Fence_green)
                        )
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${uiState.expensePercentage}%",
                                modifier = Modifier.padding(start = 8.dp),
                                color = Honeydew,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontSize = 12.sp,
                                )
                            )
                            Text(
                                text = "$${String.format("%.2f", uiState.budget)}",
                                modifier = Modifier.padding(end = 8.dp),
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Fence_green,
                                    fontSize = 12.sp,
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
                    Icon(
                        painter = painterResource(R.drawable.check),
                        contentDescription = "Check Icon",
                        tint = Void,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${uiState.expensePercentage}% Of Your Expenses, Looks Good.",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Fence_green,
                            fontSize = 14.sp,
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Transactions List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
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
                                modifier = Modifier.padding(
                                    bottom = 8.dp,
                                    top = if (globalIndex > 0) 8.dp else 0.dp
                                )
                            )
                        }

                        // Items del mes
                        itemsIndexed(
                            monthTransactions,
                            key = { _, item -> item.id }
                        ) { localIndex, transaction ->
                            val itemColor = coloresDeCirculo.getOrElse(globalIndex + localIndex) { Light_blue }

                            FoodTransactionItem(
                                transaction = transaction,
                                circleBgColor = itemColor
                            )
                        }

                        globalIndex += monthTransactions.size
                    }
                }

                // Spacer final para evitar que el último elemento quede pegado al bottom
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun FoodTransactionItem(
    transaction: FoodTransaction,
    circleBgColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Círculo con ícono
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(circleBgColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = transaction.iconId),
                contentDescription = transaction.title,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Columna de título y fecha
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    color = Void
                )
            )
            Text(
                text = transaction.dateTime,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Ocean_blue,
                    fontSize = 11.sp,
                )
            )
        }

        // Monto
        Text(
            text = "-$${String.format("%.2f", transaction.amount)}",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                color = Ocean_blue
            )
        )
    }
}


