package com.example.financeapp.ui.screen.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.components.TransactionListItem
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Fence_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.LocalThemeController
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Void

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val themeController = LocalThemeController.current
    val isDark = themeController.isDarkMode

    val coloresDeCirculo = remember {
        listOf(
            Light_blue,
            Vivid_blue,
            Ocean_blue,
            Vivid_blue,
            Light_blue,
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Transaction",
                showBackButton = true,
                centerTitle = true,
                onBackClick = { navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                } },
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = if (isDark) MaterialTheme.colorScheme.background else Caribbean_green
            )
        },
        containerColor = if (isDark) MaterialTheme.colorScheme.background else Caribbean_green
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                    navController.navigate("transaction_details")
                }
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(13.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDark) MaterialTheme.colorScheme.surface else Honeydew
                )) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Total Balance",
                        style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = if (isDark) MaterialTheme.colorScheme.onBackground else Void,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        ))
                    Text(
                        text = "$${uiState.balance}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = if (isDark) MaterialTheme.colorScheme.onBackground else Void,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }


       Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                            contentDescription = "Income Icon",
                            modifier = Modifier.size(16.dp),
                            tint = if (isDark) MaterialTheme.colorScheme.onBackground else Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Total Balance",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = if (isDark) MaterialTheme.colorScheme.onBackground else Void,
                                fontSize = 12.sp,
                            ),
                        )
                    }
                    Text(
                        text = "$${uiState.balance}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = if (isDark) MaterialTheme.colorScheme.onBackground else Honeydew,
                            fontSize = 26.sp,
                        ),
                        color = if (isDark) MaterialTheme.colorScheme.onBackground else Honeydew
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(horizontal = 14.dp),
                    thickness = 1.dp,
                    color = if (isDark) MaterialTheme.colorScheme.surfaceVariant else Light_green
                )

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
                            tint = if (isDark) MaterialTheme.colorScheme.onBackground else Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Total Expense",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = if (isDark) MaterialTheme.colorScheme.onBackground else Void,
                                fontSize = 12.sp,
                            ),
                        )
                    }
                    Text(
                        text = "-$${uiState.totalExpense}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            fontSize = 26.sp,
                        ),
                        color = if (isDark) MaterialTheme.colorScheme.tertiary else Ocean_blue
                    )
                }
            }

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
                            .background(if (isDark) MaterialTheme.colorScheme.surfaceVariant else Honeydew)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(20.dp))
                                .fillMaxWidth(uiState.expensePercentage / 100f)
                                .background(if (isDark) MaterialTheme.colorScheme.primary else Fence_green)
                        )
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${uiState.expensePercentage}%",
                                modifier = Modifier.padding(start = 8.dp),
                                color = if (isDark) MaterialTheme.colorScheme.onPrimary else Honeydew,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontSize = 12.sp,
                                )
                            )
                            Text(
                                text = "$${uiState.expenseGoal}",
                                modifier = Modifier.padding(end = 8.dp),
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = if (isDark) MaterialTheme.colorScheme.onBackground else Fence_green,
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
                        tint = if (isDark) MaterialTheme.colorScheme.onBackground else Void,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${uiState.expensePercentage}% Of Your Expenses, Looks Good.",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = if (isDark) MaterialTheme.colorScheme.onBackground else Fence_green,
                            fontSize = 14.sp,
                        )
                    )
                }
            }

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = if (isDark) MaterialTheme.colorScheme.primary else Fence_green)
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
                        .background(if (isDark) MaterialTheme.colorScheme.surface else Honeydew)
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
                                        color = if (isDark) MaterialTheme.colorScheme.onBackground else Fence_green,
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
                                        color = if (isDark) MaterialTheme.colorScheme.onBackground else Fence_green,
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
}
