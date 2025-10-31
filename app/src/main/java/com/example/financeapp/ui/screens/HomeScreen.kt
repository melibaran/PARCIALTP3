package com.example.financeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.data.sampleTransactions
import com.example.financeapp.ui.components.BalanceSummaryCard
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.components.TransactionListItem
import com.example.financeapp.ui.screen.transaction.TransactionItem as TxItem
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Honeydew
import androidx.compose.material3.HorizontalDivider

@Suppress("UNUSED_PARAMETER")
@Composable
fun HomeScreen(navController: NavController) {
    // Colores para los iconos circulares (match TransactionScreen)
    val coloresDeCirculo = remember {
        listOf(Light_blue, Vivid_blue, Ocean_blue, Vivid_blue, Light_blue)
    }
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.hi_welcome_back),
                subtitle = stringResource(R.string.good_morning),
                showBackButton = false,
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = Caribbean_green
            )
        },
        containerColor = Caribbean_green
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // Total Balance y Total Expense Row
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
                        Image(
                            painter = painterResource(id = R.drawable.arrow_up),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.total_balance),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                    Text(
                        text = "$7,783.00",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }

                com.example.financeapp.ui.components.VerticalDivider(
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
                        Image(
                            painter = painterResource(id = R.drawable.arrow_down),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.total_expense),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                    Text(
                        text = "-$1,187.40",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Ocean_blue
                        )
                    )
                }
            }

            // Progress Bar
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
                                .fillMaxWidth(0.3f)
                                .background(com.example.financeapp.ui.theme.Fence_green)
                        )
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "30%",
                                modifier = Modifier.padding(start = 8.dp),
                                color = Honeydew,
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                text = "$20,000.00",
                                modifier = Modifier.padding(end = 8.dp),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = com.example.financeapp.ui.theme.Fence_green
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
                    Image(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = "Check",
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.goal_description),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = com.example.financeapp.ui.theme.Fence_green
                        )
                    )
                }
            }

            // Transactions List con card de Savings y tabs
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
                    .padding(16.dp)
            ) {
                // Card de Savings y Revenue/Food
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Caribbean_green)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Círculo con borde bicolor y texto debajo
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                Box(
                                    modifier = Modifier.size(70.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Círculo exterior mitad blanco/azul
                                    Box(
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(CircleShape)
                                    ) {
                                        Row(modifier = Modifier.fillMaxSize()) {
                                            Box(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .fillMaxHeight()
                                                    .background(Color.White)
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .fillMaxHeight()
                                                    .background(Vivid_blue)
                                            )
                                        }
                                    }
                                    // Círculo interior verde
                                    Box(
                                        modifier = Modifier
                                            .size(58.dp)
                                            .clip(CircleShape)
                                            .background(Caribbean_green),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.car),
                                            contentDescription = null,
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Savings\nOn Goals",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                    lineHeight = 14.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(70.dp)
                                    .background(Color.White)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.salary),
                                        contentDescription = null,
                                        modifier = Modifier.size(28.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = stringResource(R.string.revenue_last_week),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onBackground
                                        )
                                        Text(
                                            text = "$4,000.00",
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onBackground
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                HorizontalDivider(
                                    color = Color.White.copy(alpha = 0.9f),
                                    thickness = 1.dp
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.food),
                                        contentDescription = null,
                                        modifier = Modifier.size(28.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = stringResource(R.string.food_last_week),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onBackground
                                        )
                                        Text(
                                            text = "-$100.00",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Ocean_blue
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Tabs
                item {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        shape = RoundedCornerShape(50),
                        color = Light_green,
                        shadowElevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            var selectedIndex by remember { mutableStateOf(2) }
                            val tabs = listOf(
                                stringResource(R.string.daily),
                                stringResource(R.string.weekly),
                                stringResource(R.string.monthly)
                            )
                            tabs.forEachIndexed { i, t ->
                                val selected = i == selectedIndex
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(50))
                                        .background(if (selected) Caribbean_green else Color.Transparent)
                                        .clickable { selectedIndex = i }
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = t,
                                        color = if (selected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                                    )
                                }
                            }
                        }
                    }
                }

                // Lista de transacciones
                val filtered = sampleTransactions.filter { it.titleResId != R.string.transport }
                itemsIndexed(filtered) { index, tx ->
                    val iconId =
                        if (tx.iconResId == R.drawable.salary) R.drawable.salary_white else tx.iconResId
                    val mapped = TxItem(
                        id = "tx_$index",
                        iconId = iconId,
                        title = stringResource(tx.titleResId),
                        dateTime = stringResource(tx.dateResId),
                        category = stringResource(tx.categoryResId),
                        amount = tx.amount,
                        isIncome = tx.amount >= 0,
                        month = ""
                    )

                    val circleColor =
                        coloresDeCirculo.getOrElse(index % coloresDeCirculo.size) { Light_blue }

                    TransactionListItem(
                        transaction = mapped,
                        circleBgColor = circleColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}
