package com.example.financeapp.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.data.sampleTransactions
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.components.TransactionListItem
import com.example.financeapp.ui.screen.transaction.TransactionItem as TxItem
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Fence_green
import com.example.financeapp.ui.theme.LocalDarkMode
import androidx.compose.material3.HorizontalDivider
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity

@Suppress("UNUSED_PARAMETER")
@Composable
fun HomeScreen(navController: NavController) {
    val darkModeState = LocalDarkMode.current
    val isDarkMode = darkModeState.isDarkMode
    
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
                onNotificationClick = { navController.navigate("notifications")}
            )
        },
        containerColor = if (isDarkMode) Fence_green else Caribbean_green
    ) { innerPadding ->
        val layoutDirection = androidx.compose.ui.platform.LocalLayoutDirection.current
        var headerHeightPx by remember { mutableStateOf(0) }
        val density = LocalDensity.current

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(
                start = innerPadding.calculateStartPadding(layoutDirection),
                top = innerPadding.calculateTopPadding(),

            )
        ) {
            val headerHeightDp = with(density) { headerHeightPx.toDp() }
            val surfaceTopPadding = headerHeightDp + 12.dp

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = surfaceTopPadding)
                    .align(Alignment.BottomStart),
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                color = if (isDarkMode) Fence_green else Honeydew,
                tonalElevation = 0.dp
            ) {
                val lazyState = rememberLazyListState()
                LazyColumn(
                    state = lazyState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Card de Savings y Revenue/Food
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = if (isDarkMode) Light_green else Caribbean_green)
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
                                        .fillMaxHeight()
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

                    item {
                        // Tabs
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
                }
            }

            // BalanceHeader superpuesto arriba, sobre el Surface
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .onGloballyPositioned { coordinates ->
                        headerHeightPx = coordinates.size.height
                    }
            ) {
                BalanceHeader(
                    totalBalance = 7783.00,
                    totalExpense = 1187.40,
                    budget = 20000.00,
                    progressPercentage = 30
                )
            }
        }
    }
}
