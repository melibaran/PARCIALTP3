package com.example.financeapp.ui.screen.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Text
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
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Fence_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Light_green
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
            TopAppBar(
                title = {
                    Text(
                        "Transaction",
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
                    IconButton(onClick = { /* TODO */ }) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            androidx.compose.foundation.Image(
                                painter = painterResource(R.drawable.bell),
                                contentDescription = "Notifications",
                                modifier = Modifier.padding(6.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Caribbean_green
                )
            )
        },
        containerColor = Caribbean_green
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Balance Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(13.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Honeydew
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
                            color = Void,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        ))
                    Text(
                        text = "$${uiState.balance}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Void,
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
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Total Balance",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Void,
                                fontSize = 12.sp,
                            ),
                        )
                    }
                    Text(
                        text = "$${uiState.balance}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Void,
                            fontSize = 26.sp,
                        ),
                        color = Honeydew
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(horizontal = 14.dp),
                    thickness = 1.dp,
                    color = Light_green
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
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Total Expense",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Void,
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
                        color = Ocean_blue
                    )
                }
            }

            // Column de barra:
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
                                text = "$${uiState.expenseGoal}",
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

            // Transactions List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
                    .padding(16.dp)
            ) {
                item {
                    Text(
                        "April",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            color = Fence_green,
                            fontSize = 20.sp,
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                itemsIndexed(
                    viewModel.getTransactionsByMonth("April"),
                    key = { _, item -> item.id }
                ) { index, transaction ->
                    val itemColor = coloresDeCirculo.getOrElse(index) { Light_blue }

                    TransactionListItem(
                        transaction = transaction,
                        circleBgColor = itemColor
                    )
                }

                val marchStartIndex = viewModel.getTransactionsByMonth("April").size

                item {
                    Text(
                        "March",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            color = Fence_green,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                itemsIndexed(
                    viewModel.getTransactionsByMonth("March"),
                    key = { _, item -> item.id }
                ) { index, transaction ->
                    val globalIndex = marchStartIndex + index
                    val itemColor = coloresDeCirculo.getOrElse(globalIndex) { Light_blue }

                    TransactionListItem(
                        transaction = transaction,
                        circleBgColor = itemColor
                    )
                }
            }
        }
    }
}
