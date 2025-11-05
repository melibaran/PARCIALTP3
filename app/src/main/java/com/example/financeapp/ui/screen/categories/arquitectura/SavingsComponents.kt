package com.example.financeapp.ui.screen.categories.arquitectura

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.financeapp.R
import com.example.financeapp.ui.screen.categories.savings.Deposit
import com.example.financeapp.ui.theme.*

@Composable
fun SavingsGoalHeader(
    iconId: Int,
    title: String,
    savedAmount: Double,
    goalAmount: Double,
    progressPercentage: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Icono del objetivo
        Box(
            modifier = Modifier.fillMaxWidth(),
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
                    painter = painterResource(id = iconId),
                    contentDescription = title,
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
                        text = "$${String.format("%.2f", savedAmount)}",
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
                        text = "$${String.format("%.2f", goalAmount)}",
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
                    progress = { progressPercentage / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    color = Caribbean_green,
                    trackColor = Light_blue
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${progressPercentage}% Complete",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 12.sp,
                        color = Void.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Cantidad restante
                val remaining = goalAmount - savedAmount
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
}

@Composable
fun SavingsDepositList(
    deposits: Map<String, List<Deposit>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
            .background(Honeydew)
            .padding(16.dp)
    ) {
        deposits.forEach { (month, monthDeposits) ->
            item(key = "header_$month") {
                Text(
                    text = month,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                        color = Fence_green,
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                )
            }

            items(monthDeposits, key = { "${month}_${it.title}_${it.date}_${it.time}" }) { deposit ->
                SavingsDepositItem(deposit = deposit)
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

@Composable
private fun SavingsDepositItem(deposit: Deposit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
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

