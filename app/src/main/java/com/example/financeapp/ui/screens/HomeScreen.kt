package com.example.financeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.data.sampleTransactions
import com.example.financeapp.ui.components.BalanceSummaryCard
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Ocean_blue
import androidx.compose.material3.HorizontalDivider

@Suppress("UNUSED_PARAMETER")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.hi_welcome_back),
                subtitle = stringResource(R.string.good_morning),
                showBackButton = false,
                onNotificationClick = { /* TODO */ }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Top balance card
            item {
                BalanceSummaryCard(
                    totalBalance = 7783.00,
                    totalExpense = 1187.40,
                    progress = 0.3f,
                    goalAmount = 20000.00,
                    description = stringResource(R.string.goal_description)
                )
            }

            // Card central y tabs
            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(Light_green)
                        .padding(20.dp)
                ) {
                    // Card central
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

                            // Outer white circle to make the split circle pop
                            Box(modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(Color.White), contentAlignment = Alignment.Center) {
                                // Inner split circle (slightly smaller)
                                Box(modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)) {
                                    Row(modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(36.dp))) {
                                        Box(modifier = Modifier
                                            .weight(1f)
                                            .background(Color.White))
                                        Box(modifier = Modifier
                                            .weight(1f)
                                            .background(Vivid_blue))
                                    }
                                    Image(painter = painterResource(id = R.drawable.car), contentDescription = null, modifier = Modifier.size(36.dp).align(Alignment.Center))
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            // Barra vertical separadora blanca (más visible)
                            Box(modifier = Modifier
                                .width(2.dp)
                                .height(56.dp)
                                .background(Color.White))

                            Spacer(modifier = Modifier.width(16.dp))

                            // Texto de la derecha (Revenue & Food) con icons
                            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(painter = painterResource(id = R.drawable.salary), contentDescription = null, modifier = Modifier.size(20.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column {
                                            Text(text = stringResource(R.string.revenue_last_week), style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onBackground)
                                            Text(text = "$4.000,00", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Línea blanca divisoria más fina
                                HorizontalDivider(color = Color.White.copy(alpha = 0.9f), thickness = 1.dp)

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(painter = painterResource(id = R.drawable.food), contentDescription = null, modifier = Modifier.size(20.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column {
                                            Text(text = stringResource(R.string.food_last_week), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                                        }
                                    }
                                    Text(text = "-100", style = MaterialTheme.typography.bodyMedium, color = Ocean_blue)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Tabs pill con sombra y espaciado, usando tint de caribbean_green
                    Surface(
                        color = Light_green,
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .shadow(6.dp, RoundedCornerShape(50))
                    ) {
                        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                            var selectedIndex by remember { mutableStateOf(2) }
                            val tabs = listOf(stringResource(R.string.daily), stringResource(R.string.weekly), stringResource(R.string.monthly))
                            tabs.forEachIndexed { i, t ->
                                val selected = i == selectedIndex
                                Box(modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(if (selected) Color.White else Color.Transparent)
                                    .then(if (selected) Modifier.border(2.dp, Caribbean_green, RoundedCornerShape(50)) else Modifier)
                                    .clickable { selectedIndex = i }
                                ) {
                                    Text(text = t, color = if (selected) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
                                }
                            }
                        }
                    }
                }
            }

            // Transactions list: filtramos Transport
            val filtered = sampleTransactions.filter { it.titleResId != R.string.transport }
            items(filtered) { tx ->
                // Cada item: usar salary_white para el ícono de salario y texto más pequeño
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp), verticalAlignment = Alignment.CenterVertically) {

                        val iconId = if (tx.iconResId == R.drawable.salary) R.drawable.salary_white else tx.iconResId
                        val bgBrush = when (tx.iconResId) {
                            R.drawable.salary -> Brush.linearGradient(listOf(Light_blue, Vivid_blue), tileMode = TileMode.Clamp)
                            R.drawable.groceries -> Brush.linearGradient(listOf(Light_blue, Vivid_blue), tileMode = TileMode.Clamp)
                            R.drawable.rent -> Brush.linearGradient(listOf(Vivid_blue, Ocean_blue), tileMode = TileMode.Clamp)
                            else -> Brush.linearGradient(listOf(Light_blue, Vivid_blue), tileMode = TileMode.Clamp)
                        }

                        Box(modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(brush = bgBrush), contentAlignment = Alignment.Center) {
                            Image(painter = painterResource(id = iconId), contentDescription = null, modifier = Modifier.size(24.dp))
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = stringResource(tx.titleResId), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                            Text(text = stringResource(tx.dateResId), style = MaterialTheme.typography.bodySmall, color = Vivid_blue)
                        }

                        // Divider vertical delgado
                        Box(modifier = Modifier
                            .width(1.dp)
                            .height(36.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        // Categoría o frecuencia
                        Text(text = stringResource(tx.categoryResId), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)

                        Spacer(modifier = Modifier.width(12.dp))

                        // Divider vertical antes de la cantidad
                        Box(modifier = Modifier
                            .width(1.dp)
                            .height(36.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        // Cantidad
                        val amountText = if (tx.amount < 0) String.format(java.util.Locale.US, "-$%,.2f", -tx.amount) else String.format(java.util.Locale.US, "$%,.2f", tx.amount)
                        val amountColor = if (tx.amount < 0) Ocean_blue else MaterialTheme.colorScheme.onBackground
                        Text(text = amountText, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = amountColor)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}
