package com.example.financeapp.ui.screen.categories.savings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavingsDetailScreen(
    navController: NavController,
    goalTitle: String,
    viewModel: SavingsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val goal = uiState.savingsGoals.find { it.title == goalTitle }

    if (goal == null) {
        // Si no se encuentra el objetivo, volver atrás
        navController.navigateUp()
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = goal.title,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            fontSize = 20.sp,
                            color = Honeydew
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.bring_back),
                            contentDescription = "Back",
                            tint = Honeydew,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Caribbean_green
                )
            )
        },
        containerColor = Caribbean_green
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            // Header con el icono y progreso
            item {
                Spacer(modifier = Modifier.height(24.dp))

                // Icono del objetivo
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Light_blue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = goal.painter,
                            contentDescription = goal.title,
                            modifier = Modifier.size(70.dp),
                            tint = Color.Unspecified
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Información del objetivo
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Honeydew),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        // Cantidad ahorrada
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Saved Amount",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontSize = 14.sp,
                                    color = Void
                                )
                            )
                            Text(
                                text = "$${String.format("%.2f", goal.savedAmount)}",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                    fontSize = 16.sp,
                                    color = Caribbean_green
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Meta
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Goal Amount",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontSize = 14.sp,
                                    color = Void
                                )
                            )
                            Text(
                                text = "$${String.format("%.2f", goal.goalAmount)}",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                    fontSize = 16.sp,
                                    color = Void
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Barra de progreso
                        LinearProgressIndicator(
                            progress = goal.progressPercentage / 100f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(12.dp)
                                .clip(RoundedCornerShape(6.dp)),
                            color = Caribbean_green,
                            trackColor = Light_blue
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${goal.progressPercentage}% Complete",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 12.sp,
                                color = Void.copy(alpha = 0.6f)
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Cantidad restante
                        val remaining = goal.goalAmount - goal.savedAmount
                        Text(
                            text = "$${String.format("%.2f", remaining)} remaining to reach your goal",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 13.sp,
                                color = Void
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Título de depósitos
                Text(
                    text = "Deposit History",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                        fontSize = 18.sp,
                        color = Honeydew
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Lista de depósitos por mes
            goal.deposits.forEach { (month, deposits) ->
                item {
                    Text(
                        text = month,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 14.sp,
                            color = Honeydew.copy(alpha = 0.8f)
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                items(deposits) { deposit ->
                    DepositItem(deposit = deposit)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Espacio al final
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun DepositItem(deposit: Deposit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Honeydew),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = deposit.title,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        color = Void
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${deposit.date} • ${deposit.time}",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp,
                        color = Void.copy(alpha = 0.6f)
                    )
                )
            }

            Text(
                text = "+$${String.format("%.2f", deposit.amount)}",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    fontSize = 16.sp,
                    color = Caribbean_green
                )
            )
        }
    }
}

